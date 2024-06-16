package smb.conf.dynreconf.analysis.time

import smb.conf.dynreconf.EvaluationPath
import smb.conf.dynreconf.utils.std
import java.io.File
import java.io.FileWriter

private val outputFile = File(EvaluationPath.get("study_results", "time", "projects.csv"))
private val output = FileWriter(outputFile)

fun main() {
    val numberOfBenchmarks = getNumberOfBenchmarks(File(EvaluationPath.get("study_results", "time", "cov.csv")))
    val cov = getTimeSaved(File(EvaluationPath.get("study_results", "time", "cov.csv")))
    val ci = getTimeSaved(File(EvaluationPath.get("study_results", "time", "ci.csv")))
    val divergence = getTimeSaved(File(EvaluationPath.get("study_results", "time", "divergence.csv")))

    output.appendln("project;covAbsolute;covMean;covStd;ciAbsolute;ciMean;ciStd;divergenceAbsolute;divergenceMean;divergenceStd")
    cov.keys.forEach { project ->
        val defaultExecutionTime = numberOfBenchmarks.getValue(project) * 500
        output.append(project)
        output.append(";${cov.getValue(project).average()*defaultExecutionTime/3600}")
        output.append(";${cov.getValue(project).average()}")
        output.append(";${cov.getValue(project).std()}")
        output.append(";${ci.getValue(project).average()*defaultExecutionTime/3600}")
        output.append(";${ci.getValue(project).average()}")
        output.append(";${ci.getValue(project).std()}")
        output.append(";${divergence.getValue(project).average()*defaultExecutionTime/3600}")
        output.append(";${divergence.getValue(project).average()}")
        output.append(";${divergence.getValue(project).std()}")
        output.appendln("")
    }

    val defaultExecutionTime = numberOfBenchmarks.values.sum() * 500
    output.append("total")
    output.append(";${cov.values.flatten().average()*defaultExecutionTime/3600}")
    output.append(";${cov.values.flatten().average()}")
    output.append(";${cov.values.flatten().std()}")
    output.append(";${ci.values.flatten().average()*defaultExecutionTime/3600}")
    output.append(";${ci.values.flatten().average()}")
    output.append(";${ci.values.flatten().std()}")
    output.append(";${divergence.values.flatten().average()*defaultExecutionTime/3600}")
    output.append(";${divergence.values.flatten().average()}")
    output.append(";${divergence.values.flatten().std()}")
    output.appendln("")
    output.flush()
}

private fun getTimeSaved(file: File): Map<String, List<Double>> {
    val ret = mutableMapOf<String, MutableList<Double>>()

    file.forEachLine {
        if (it == "project;commits;benchmarks;params;forks;iteration1;iteration2;iteration3;iteration4;iteration5;time") {
            return@forEachLine
        }

        val parts = it.split(";")
        val project = parts[0]
        val time = parts[10].toDouble()

        if (ret[project] == null) {
            ret[project] = mutableListOf()
        }

        val timeSaved = (500 - time) / 500
        ret.getValue(project).add(timeSaved)
    }

    return ret
}

private fun getNumberOfBenchmarks(file: File): Map<String, Int> {
    val ret = mutableMapOf<String, Int>()

    file.forEachLine {
        if (it == "project;commits;benchmarks;params;forks;iteration1;iteration2;iteration3;iteration4;iteration5;time") {
            return@forEachLine
        }

        val parts = it.split(";")
        val project = parts[0]

        if (ret[project] == null) {
            ret[project] = 0
        }

        ret[project] = ret.getValue(project) + 1
    }

    return ret
}