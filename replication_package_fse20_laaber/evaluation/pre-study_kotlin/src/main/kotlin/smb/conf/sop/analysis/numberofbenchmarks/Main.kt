package smb.conf.sop.analysis.numberofbenchmarks

import smb.conf.sop.EvaluationPath
import smb.conf.sop.analysis.yearInSeconds
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.CustomMappingStrategy
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File

private val output = mutableListOf<ResNumberOfBenchmarks>()

fun main() {
    val fileProjects = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val outputFile = File(EvaluationPath.get("pre-study_results", "aggregated", "numberofbenchmarks.csv")).toPath()
    val projects = CsvProjectParser(fileProjects).getList()

    projects.filter { it.mainRepo == true }
            .forEach {
                output.add(ResNumberOfBenchmarks(it.project, it.numberOfBenchmarks!!))
            }

    OpenCSVWriter.write(outputFile, output, CustomMappingStrategy(ResNumberOfBenchmarks::class.java))
}