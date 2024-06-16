package smb.conf.sop.datapreparation.commithash

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.OpenCSVWriter
import smb.conf.sop.utils.toFileSystemName
import java.io.File
import java.nio.file.Paths

fun main() {
    val input = EvaluationPath.get("pre-study_results", "current-commit", "results")
    val projectFile = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val projects = CsvProjectParser(projectFile).getList()

    projects.forEach {
        val file = Paths.get(input, "${it.project.toFileSystemName}.csv").toFile()

        if (file.exists()) {
            val lines = file.readLines()
            if (lines.isNotEmpty()) {
                val firstLine = lines.first()
                val (_, commitHash, _) = firstLine.split(",")
                it.lastCommitHash = commitHash.replace("\"", "")
            }
        }
    }

    OpenCSVWriter.write(projectFile.toPath(), projects)
}