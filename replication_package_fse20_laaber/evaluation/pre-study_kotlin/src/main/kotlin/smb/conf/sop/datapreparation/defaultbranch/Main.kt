package smb.conf.sop.datapreparation.defaultbranch

import smb.conf.sop.datapreparation.github.mapper
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.CustomMappingStrategy
import smb.conf.sop.utils.OpenCSVWriter
import smb.conf.sop.utils.Row
import org.apache.commons.io.IOUtils
import smb.conf.sop.EvaluationPath
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

val token = ""

fun main() {
    val file = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val projects = CsvProjectParser(file).getList()

    projects.forEachIndexed { i, item ->
        val project = item.project

        val url = "https://api.github.com/repos/$project"
        val obj = URL(url)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        con.setRequestProperty("Authorization", "token $token")

        println("(${con.getHeaderField("X-RateLimit-Remaining")}) $project: ${con.responseCode}")

        if (con.responseCode != 404) {
            val jsonString = IOUtils.toString(con.inputStream, Charset.defaultCharset())
            val json = mapper.readTree(jsonString)

            item.defaultBranch = json["default_branch"].textValue()
        }
    }

    OpenCSVWriter.write(file.toPath(), projects, CustomMappingStrategy(Row::class.java))
}