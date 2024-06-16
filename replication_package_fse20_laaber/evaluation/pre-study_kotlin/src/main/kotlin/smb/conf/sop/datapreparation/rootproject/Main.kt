package smb.conf.sop.datapreparation.rootproject

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File

fun main() {
    val csv = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val list = CsvProjectParser(csv).getList()

    val parent = mutableMapOf<String, String?>()

    list.forEach {
        if (it.parentProject != null) {
            parent[it.project] = it.parentProject
        }
    }

    list.forEach {
        if (it.repoAvailable) {
            var lastParent: String? = it.project
            var currentParent = it.parentProject

            while (!currentParent.isNullOrBlank()) {
                lastParent = currentParent
                currentParent = parent[currentParent]
            }

            it.rootProject = lastParent
        }
    }

    OpenCSVWriter.write(csv.toPath(), list)
}