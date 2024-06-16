package smb.conf.sop.datapreparation.mainproject

import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.OpenCSVWriter
import org.apache.logging.log4j.LogManager
import smb.conf.sop.EvaluationPath
import java.io.File

private val log = LogManager.getLogger()

fun main() {
    val csv = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val list = CsvProjectParser(csv).getList()

    val numberOfStars = mutableMapOf<String, MutableList<Pair<String, Int>>>()
    val isMain = mutableListOf<String>()

    list.forEach {
        if (it.repoAvailable) {
            if (numberOfStars[it.rootProject] == null) {
                numberOfStars[it.rootProject!!] = mutableListOf()
            }

            numberOfStars.getValue(it.rootProject!!).add(Pair(it.project, it.stars!!))
        }
    }

    numberOfStars.forEach { (name, list) ->
        list.sortByDescending { it.second }
        val mainName = list.first().first

        val root = list.find { it.first == name }
        if (root!!.first != mainName) {
            log.info("$name root repo has not most stars")
        }

        isMain.add(mainName)
    }

    list.forEach {
        if (isMain.contains(it.project)) {
            it.mainRepo = true
        }
    }

    OpenCSVWriter.write(csv.toPath(), list)
}