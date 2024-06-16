package smb.conf.sop.datapreparation.firstbenchmarkfound

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.CsvResultParser
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File

fun main() {
    val fileHistory = File(EvaluationPath.get("pre-study_results", "history", "merged.csv"))
    val fileProjects = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val outFile = File("D:\\new.csv").toPath()

    val items = CsvResultParser(fileHistory).getList()
    val projects = CsvProjectParser(fileProjects).getList()

    val firstFound = items.groupBy { it.project }
            .map { (project, list) ->
                project to list.map { it.commitTime!! }.min()!!
            }
            .toMap()

    projects.forEach {
        it.firstBenchmarkFound = firstFound[it.project]
    }

    OpenCSVWriter.write(outFile, projects)
}