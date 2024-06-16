package smb.conf.sop.datapreparation.lastcommit

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File

fun main() {
    val input = File(EvaluationPath.get("pre-study_results", "history", "history-selected-commits.csv"))
    val projectFile = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val projects = CsvProjectParser(projectFile).getList()

    val map = mutableMapOf<String, Pair<Int, String>>()
    input.forEachLine {
        if (it == "project,commitTime,commitId") {
            return@forEachLine
        }
        val (project, time, commitId) = it.split(",")
        if (map[project] == null) {
            map[project] = Pair(time.toInt(), commitId)
        }
    }

    map.forEach { (key, value) ->
        val project = projects.first { it.project == key }
        project.lastCommit = value.first
    }

    OpenCSVWriter.write(projectFile.toPath(), projects)
}