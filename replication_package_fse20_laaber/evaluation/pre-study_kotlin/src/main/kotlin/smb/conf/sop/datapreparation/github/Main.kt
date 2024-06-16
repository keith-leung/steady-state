package smb.conf.sop.datapreparation.github

import smb.conf.sop.utils.CustomMappingStrategy
import smb.conf.sop.utils.OpenCSVWriter
import smb.conf.sop.utils.Row
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import smb.conf.sop.EvaluationPath
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

val mapper = ObjectMapper()
val token = ""

fun main() {
    val inFile = File(EvaluationPath.get("pre-study_subjects", "list_to_projects"))
    val outFile = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv")).toPath()
    val res = mutableListOf<Row>()

    inFile.forEachLine { line ->
        val item = Row()
        item.project = line

        val url = "https://api.github.com/repos/$line"
        val obj = URL(url)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        con.setRequestProperty("Authorization", "token $token")

        println("(${con.getHeaderField("X-RateLimit-Remaining")}) $line: ${con.responseCode}")

        if (con.responseCode == 404) {
            item.repoAvailable = false
        } else {
            val jsonString = IOUtils.toString(con.inputStream, Charset.defaultCharset())
            val json = mapper.readTree(jsonString)

            val fork = json["fork"].booleanValue()

            item.forked = fork
            item.stars = json["stargazers_count"].intValue()
            item.forks = json["forks_count"].intValue()
            item.watchers = json["subscribers_count"].intValue()
            item.repoAvailable = true
            item.cloneUrl = json["clone_url"].textValue()
            item.lastUpdate = json["updated_at"].textValue()
            item.numberOfCommits = getNumberOfCommits(item.project)
            item.numberOfTags = getNumberOfTags(item.project)
            item.numberOfContributors = getNumberOfContributors(item.project)

            if (fork) {
                item.parentProject = json["parent"]["full_name"].textValue()
            }
        }

        res.add(item)
    }

    OpenCSVWriter.write(outFile, res, CustomMappingStrategy(Row::class.java))
}

private fun getNumberOfCommits(name: String): Int {
    val url = "https://api.github.com/repos/$name/commits?per_page=100"
    return callGitHubApi(url)
}

private fun getNumberOfTags(name: String): Int {
    val url = "https://api.github.com/repos/$name/tags?per_page=100"
    return callGitHubApi(url)
}

private fun getNumberOfContributors(name: String): Int {
    val url = "https://api.github.com/repos/$name/contributors?per_page=100"
    return callGitHubApi(url)
}

private fun callGitHubApi(url: String): Int {
    val con = URL(url).openConnection() as HttpURLConnection
    con.requestMethod = "GET"
    con.setRequestProperty("Authorization", "token $token")

    val jsonString = IOUtils.toString(con.inputStream, Charset.defaultCharset())
    val json = mapper.readTree(jsonString)

    val link = con.getHeaderField("Link")
    return if (link == null) {
        json.count()
    } else {
        val linkItems = link.split(",")
        val linkItemLast = linkItems.filter { it.contains("rel=\"last\"") }.firstOrNull()
        if (linkItemLast == null) {
            json.count()
        } else {
            val currentPage = url.substringAfter("&page=", "1").toInt()

            val page = linkItemLast.substringAfter("&page=").substringBefore(">").trim().toInt()
            if (currentPage == page) {
                100 * (page - 1) + json.count()
            } else {
                100 * (page - 1) + callGitHubApi("$url&page=$page")
            }
        }
    }
}