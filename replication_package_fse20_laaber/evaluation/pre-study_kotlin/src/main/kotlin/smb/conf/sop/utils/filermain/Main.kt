package smb.conf.sop.utils.filermain

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.toGithubName
import java.io.File

fun main() {
    val inputDir = File(EvaluationPath.get("pre-study_results", "projects", "current-commit"))
    val outputDir = File(EvaluationPath.get("pre-study_results", "projects", "current-filtered"))
    val csv = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))

    val projects = CsvProjectParser(csv).getList()
    val isMain = projects.map {
        it.project to (it.mainRepo ?: false)
    }.toMap()

    inputDir.walk().forEach {
        if (it.isFile) {
            val project = it.nameWithoutExtension.toGithubName
            if (isMain[project] == true) {
                val newFile = File("${outputDir.absolutePath}/${it.name}")
                it.copyTo(newFile)
            }
        }
    }
}