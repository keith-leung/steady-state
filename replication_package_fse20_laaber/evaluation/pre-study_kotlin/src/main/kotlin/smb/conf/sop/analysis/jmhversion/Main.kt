package smb.conf.sop.analysis.jmhversion

import ch.uzh.ifi.seal.bencher.JMHVersion
import smb.conf.sop.EvaluationPath
import smb.conf.sop.analysis.jmhDate
import smb.conf.sop.analysis.yearInSeconds
import smb.conf.sop.utils.CsvProjectParser
import smb.conf.sop.utils.CustomMappingStrategy
import smb.conf.sop.utils.OpenCSVWriter
import java.io.File
import kotlin.math.round

private val output = mutableListOf<ResJmhVersion>()

fun main() {
    val file = File(EvaluationPath.get("pre-study_subjects", "pre-study_subjects.csv"))
    val outputFile = File(EvaluationPath.get("pre-study_results", "aggregated", "jmhversion.csv")).toPath()

    val items = CsvProjectParser(file).getList()

    val list = items
            .filter {
                it.mainRepo == true && it.numberOfBenchmarks!! > 0
            }
            .map {
                val version = convertJmhVersionWithoutPatch(it.jmhVersion)
                val useJmhSince = if (it.lastCommit == null || it.firstBenchmarkFound == null) {
                    0.0
                } else {
                    round((it.lastCommit!! - it.firstBenchmarkFound!!) / yearInSeconds * 100) / 100.0
                }

                Pair(version, useJmhSince)
            }
            .filter { it.first != null }

    val shortLived = list.filter { it.second < 1 }
            .map { it.first }
            .filterNotNull()
            .groupingBy { it }
            .eachCount()

    val longLived = list.filter { it.second >= 1 }
            .map { it.first }
            .filterNotNull()
            .groupingBy { it }
            .eachCount()

    val versions = jmhDate.toList().filter { it.first.patch == 0 }.reversed()

    versions.forEach { (version, _) ->
        val short = shortLived[version] ?: 0
        val long = longLived[version] ?: 0
        output.add(ResJmhVersion(version, short + long, short, long))
    }

    OpenCSVWriter.write(outputFile, output, CustomMappingStrategy(ResJmhVersion::class.java))
}

private fun convertJmhVersionWithoutPatch(input: String?): JMHVersion? {
    return if (input == null || input.isNullOrBlank()) {
        null
    } else {
        val list = input.split(".")
        val major = list[0].toInt()
        val minor = list[1].toInt()
        JMHVersion(major, minor)
    }
}