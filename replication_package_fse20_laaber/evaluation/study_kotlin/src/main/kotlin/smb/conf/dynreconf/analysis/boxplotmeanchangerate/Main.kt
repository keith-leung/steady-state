package smb.conf.dynreconf.analysis.boxplotmeanchangerate

import smb.conf.dynreconf.EvaluationPath
import java.io.File
import java.io.FileWriter
import kotlin.math.abs

private val outputFile = File(EvaluationPath.get("study_results", "variability", "boxplot_meanchangerate_cov.csv"))
//private val outputFile = File(EvaluationPath.get("study_results", "variability", "boxplot_meanchangerate_ci.csv"))
//private val outputFile = File(EvaluationPath.get("study_results", "variability", "boxplot_meanchangerate_divergence.csv"))
private val output = FileWriter(outputFile)

fun main() {
    val file = File(EvaluationPath.get("study_results", "variability"))

    val map = mutableMapOf<String, List<Double>>()
    file.walk().forEach {
        if (it.isFile && it.name == "variability.csv" && it.parent != file.absolutePath) {
            val name = it.parentFile.name
            val list = eval(it)
            map[name] = list
        }
    }

    val lastKey = map.keys.last()

    map.keys.forEach { key ->
        output.append(key)
        if (key != lastKey) {
            output.append(";")
        }
    }
    output.appendln()

    val maxLength = map.values.map { it.size }.max()!!
    for (line in 0 until maxLength) {
        map.keys.forEach { key ->
            val list = map.getValue(key)
            if (line < list.size) {
                output.append("${list[line]}")
            }

            if (key != lastKey) {
                output.append(";")
            }
        }
        output.appendln()
    }

    output.flush()
}

private fun eval(file: File): List<Double> {
    val ret = mutableListOf<Double>()
    file.forEachLine {
        if (it == "project;commit;benchmark;params;meanDefault;meanCov;meanCi;meanKld;ciPercentageDefault;ciPercentageCov;ciPercentageCi;ciPercentageKld;effectSizeCov;effectSizeCi;effectSizeKld;wilcoxonCov;wilcoxonCi;wilcoxonKld;ratioMeanCov;ratioLowerCov;ratioUpperCov;ratioMeanCi;ratioLowerCi;ratioUpperCi;ratioMeanKld;ratioLowerKld;ratioUpperKld") {
            return@forEachLine
        }

        val parts = it.split(";")
        val meanDefault = parts[4].toDouble()
        // cov
        val meanCustom = parts[5].toDouble()
        // ci
//        val meanCustom = parts[6].toDouble()
        // divergence
//        val meanCustom = parts[7].toDouble()

        ret.add(meanChangeRate(meanDefault, meanCustom))
    }

    return ret
}


private fun meanChangeRate(before: Double, after: Double): Double {
    return if ((after / before).isNaN()) {
        0.0
    } else {
        abs((after / before) - 1)
    }
}