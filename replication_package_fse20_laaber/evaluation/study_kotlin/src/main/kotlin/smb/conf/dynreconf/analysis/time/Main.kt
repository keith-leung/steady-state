package smb.conf.dynreconf.analysis.time

import smb.conf.dynreconf.EvaluationPath
import java.io.File
import java.io.FileWriter
import java.nio.file.Paths

private val outputFileCov = File(EvaluationPath.get("study_results", "time", "cov.csv"))
private val outputFileCi = File(EvaluationPath.get("study_results", "time", "ci.csv"))
private val outputFileDivergence = File(EvaluationPath.get("study_results", "time", "divergence.csv"))
private val outputCov = FileWriter(outputFileCov)
private val outputCi = FileWriter(outputFileCi)
private val outputDivergence = FileWriter(outputFileDivergence)
private val thresholdCov = 0.0088
private val thresholdCi = 0.1092
private val thresholdDivergence = 0.0432

fun main() {
    outputCov.appendln("project;commits;benchmarks;params;forks;iteration1;iteration2;iteration3;iteration4;iteration5;time")
    outputCi.appendln("project;commits;benchmarks;params;forks;iteration1;iteration2;iteration3;iteration4;iteration5;time")
    outputDivergence.appendln("project;commits;benchmarks;params;forks;iteration1;iteration2;iteration3;iteration4;iteration5;time")
    val input = File(EvaluationPath.get("study_results", "variability"))
    input.list().forEach {
        val folder = Paths.get(input.absolutePath, it).toFile()

        if (folder.isDirectory) {
            val covFile = Paths.get(folder.absolutePath, "outputCovTotal.csv").toFile()
            run(covFile, thresholdCov, outputCov)
            val ciFile = Paths.get(folder.absolutePath, "outputCiTotal.csv").toFile()
            run(ciFile, thresholdCi, outputCi)
            val divergenceFile = Paths.get(folder.absolutePath, "outputDivergenceTotal.csv").toFile()
            run(divergenceFile, thresholdDivergence, outputDivergence)
        }
    }

    outputCov.flush()
    outputCi.flush()
    outputDivergence.flush()
}

private fun run(file: File, threshold: Double, fw: FileWriter) {
    file.forEachLine {
        if (it == "project;commit;benchmark;params;threshold;f2;f3;f4;f5;reachedForks;reachedF1;reachedF2;reachedF3;reachedF4;reachedF5") {
            return@forEachLine
        }

        val parts = it.split(";")

        var numberOfWarmupIterations = 0
        val forks = getForkValue(parts[9].toInt())
        val iteration1 = getIterationValue(parts[10].toInt())
        val iteration2 = getIterationValue(parts[11].toInt())
        var iteration3 = getIterationValue(parts[12].toInt())
        var iteration4 = getIterationValue(parts[13].toInt())
        var iteration5 = getIterationValue(parts[14].toInt())

        numberOfWarmupIterations += iteration1
        numberOfWarmupIterations += iteration2

        if (forks >= 3) {
            numberOfWarmupIterations += iteration3
        } else {
            iteration3 = 0
        }

        if (forks >= 4) {
            numberOfWarmupIterations += iteration4
        } else {
            iteration4 = 0
        }

        if (forks == 5) {
            numberOfWarmupIterations += iteration5
        } else {
            iteration5 = 0
        }

        val totalTime = numberOfWarmupIterations * 1 * (1 + threshold) + forks * 10 * 1

        fw.appendln("${parts[0]};${parts[1]};${parts[2]};${parts[3]};$forks;$iteration1;$iteration2;$iteration3;$iteration4;$iteration5;$totalTime")
    }
}

private fun getForkValue(value: Int): Int {
    return if (value == Int.MAX_VALUE) {
        5
    } else {
        value
    }
}

private fun getIterationValue(value: Int): Int {
    return if (value == Int.MAX_VALUE) {
        50
    } else {
        value
    }
}