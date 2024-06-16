package smb.conf.dynreconf.analysis.variability

import smb.conf.dynreconf.EvaluationPath
import smb.conf.dynreconf.utils.std
import java.io.File
import kotlin.math.abs

fun main() {
    val file = File(EvaluationPath.get("study_results", "variability"))

    val reachedForks = mutableMapOf<String, Int>()
    val changeRate = mutableMapOf<String, Double>()

    file.walk().forEach {
        if (it.isFile && it.name == "outputCiTotal.csv" && it.parent != file.absolutePath) {
            reachedForks.putAll(forks(it))
        }
    }

    file.walk().forEach {
        if (it.isFile && it.name == "variability.csv" && it.parent != file.absolutePath) {
            changeRate.putAll(changeRate(it))
        }
    }

    val reached = mutableListOf<Double>()
    val notReached = mutableListOf<Double>()

    changeRate.forEach { (key, value) ->
        val notReachedAfter5Forks = reachedForks.getValue(key) == Int.MAX_VALUE

        if (notReachedAfter5Forks) {
            notReached.add(value)
        } else {
            reached.add(value)
        }
    }

    println("Reached avg: ${reached.average()} +/- ${reached.std()}")
    println("Not reached avg: ${notReached.average()} +/- ${notReached.std()}")
}

private fun forks(file: File): Map<String, Int> {
    val ret = mutableMapOf<String, Int>()
    file.forEachLine {
        if (it == "project;commit;benchmark;params;threshold;f2;f3;f4;f5;reachedForks;reachedF1;reachedF2;reachedF3;reachedF4;reachedF5") {
            return@forEachLine
        }

        val parts = it.split(";")
        val project = parts[0]
        val benchmark = parts[2]
        val params = parts[3]
        val reachedForks = parts[9].toInt()

        ret["$project#$benchmark#$params"] = reachedForks
    }

    return ret
}

private fun changeRate(file: File): Map<String, Double> {
    val ret = mutableMapOf<String, Double>()

    file.forEachLine {
        if (it == "project;commit;benchmark;params;meanDefault;meanCov;meanCi;meanKld;ciPercentageDefault;ciPercentageCov;ciPercentageCi;ciPercentageKld;effectSizeCov;effectSizeCi;effectSizeKld;wilcoxonCov;wilcoxonCi;wilcoxonKld;ratioMeanCov;ratioLowerCov;ratioUpperCov;ratioMeanCi;ratioLowerCi;ratioUpperCi;ratioMeanKld;ratioLowerKld;ratioUpperKld") {
            return@forEachLine
        }

        val parts = it.split(";")
        val project = parts[0]
        val benchmark = parts[2]
        val params = parts[3]
        val meanDefault = parts[4].toDouble()
        val meanCi = parts[6].toDouble()

        ret["$project#$benchmark#$params"] = meanChangeRate(meanDefault, meanCi)
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