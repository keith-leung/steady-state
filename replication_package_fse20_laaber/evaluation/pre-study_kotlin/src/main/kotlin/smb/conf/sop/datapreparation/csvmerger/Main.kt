package smb.conf.sop.datapreparation.csvmerger

import smb.conf.sop.EvaluationPath
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File

fun main() {
    val file = File(EvaluationPath.get("pre-study_results", "current-commit", "merged-isMain.csv"))
    val benchmarkFile = File(EvaluationPath.get("pre-study_results", "aggregated", "numberofbenchmarks.csv"))
    val csv = File(EvaluationPath.get( "pre-study_subjects", "pre-study_subjects.csv"))
    val list = CsvProjectParser(csv).getList()

    val versions = mutableMapOf<String, String?>()
    val javaVersion = mutableMapOf<String, Pair<String?, String?>>()
    file.forEachLine {
        val parts = it.split(",")
        val p = parts[0]
        val v = parts[3]
        val vt = parts[4]
        val vs = parts[5]
        if (!v.isNullOrEmpty()) {
            versions[p] = v
        }
        javaVersion[p] = Pair(vt, vs)
    }

    val benchmarks = mutableMapOf<String, Int?>()
    benchmarkFile.forEachLine {
        val (p, n) = it.split(",")
        benchmarks[p] = n.toIntOrNull()
    }

    list.forEach {
        val name = it.project
        val v = versions[name]
        val b = benchmarks[name]
        val j = javaVersion[name]

        if (v != null) {
            it.jmhVersion = v
        }

        if (b != null) {
            it.numberOfBenchmarks = b
        }

        if (j != null) {
            if (!j.first.isNullOrBlank()) {
                it.javaTarget = j.first
            }

            if (!j.second.isNullOrBlank()) {
                it.javaSource = j.second
            }
        }
    }

    OpenCSVWriter.write(csv.toPath(), list)
}