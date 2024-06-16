package smb.conf.sop.utils.resultismain

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.CsvResultParser
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File

fun main() {
    val projectFile = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val benchmarkFile = File(EvaluationPath.get("pre-study_results", "current-commit", "merged.csv"))
    val output = File(EvaluationPath.get("pre-study_results", "current-commit", "merged-isMain.csv")).toPath()
    val projects = CsvProjectParser(projectFile).getList()
    val benchmarks = CsvResultParser(benchmarkFile).getList()

    val filtered = benchmarks
        .filter { benchmark ->
            val p = projects.find {
                benchmark.project == it.project
            } ?: return@filter false
            p.mainRepo == true
        }
        .sortedBy { it.project.toLowerCase() }

    OpenCSVWriter.write(output, filtered)
}