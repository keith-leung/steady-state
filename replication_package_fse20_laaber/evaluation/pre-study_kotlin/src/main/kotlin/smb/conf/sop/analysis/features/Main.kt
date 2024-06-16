package smb.conf.sop.analysis.features

import smb.conf.sop.EvaluationPath
import smb.conf.sop.analysis.percentageString
import smb.conf.sop.utils.CsvResultParser
import smb.conf.sop.utils.CustomMappingStrategy
import smb.conf.sop.utils.OpenCSVWriter
import smb.conf.sop.utils.Result
import java.io.File
import kotlin.reflect.KMutableProperty1

private val output = mutableListOf<ResKeyValue>()

fun main() {
    val file = File(EvaluationPath.get("pre-study_results", "current-commit", "merged-isMain.csv"))
    val outputFile = File(EvaluationPath.get("pre-study_results", "aggregated", "features.csv")).toPath()
    val all = CsvResultParser(file).getList()

    val noMethodArgument = returnCountMap(all, Result::paramCount)[0] ?: 0
    output.add(ResKeyValue("Benchmark has no method argument", "$noMethodArgument (${percentageString(noMethodArgument, all.size)})"))

    hasProperty(all, "blackhole", Result::hasBlackhole)
    hasProperty(all, "control", Result::hasControl)
    hasProperty(all, "benchmarkParams", Result::hasBenchmarkParams)
    hasProperty(all, "iterationParams", Result::hasIterationParams)
    hasProperty(all, "threadParams", Result::hasThreadParams)
    jmhParams(all)
    hasReturnType(all)
    hasReturnTypeOrBlackHole(all)

    val hasGroup = returnCountMap(all, Result::partOfGroup)[true] ?: 0
    output.add(ResKeyValue("Benchmark is part of a group", "$hasGroup (${percentageString(hasGroup, all.size)})"))

    val hasNoParameterization = returnCountMap(all, Result::parameterizationCombinations)[1] ?: 0
    val hasParameterization = all.size - hasNoParameterization
    output.add(ResKeyValue("Benchmark has parameterization", "$hasParameterization (${percentageString(hasParameterization, all.size)})"))

    params(all)
    avgBenchsAndInnerClassBenchs(all)

    OpenCSVWriter.write(outputFile, output, CustomMappingStrategy(ResKeyValue::class.java))
}

private fun returnCountMap(all: Set<Result>, property: KMutableProperty1<Result, *>): Map<Any?, Int> {
    return all.groupingBy(property).eachCount().toList().sortedWith(compareBy {
        it.first as Comparable<*>
    }).toMap()
}

private fun hasProperty(all: Set<Result>, title: String, property: KMutableProperty1<Result, *>) {
    val list = all.filter { property.get(it) == true }

    output.add(ResKeyValue(title, "${list.size} of ${all.size} (${percentageString(list.size, all.size)})"))
}

private fun hasReturnType(all: Set<Result>) {
    val list = all.filter { !it.returnType.isNullOrBlank() }

    output.add(ResKeyValue("Benchmark has return type", "${list.size} of ${all.size} (${percentageString(list.size, all.size)})"))
}

private fun hasReturnTypeOrBlackHole(all: Set<Result>) {
    val list = all.filter { !it.returnType.isNullOrBlank() || it.hasBlackhole }

    output.add(ResKeyValue("Benchmark has return type or a blackhole", "${list.size} of ${all.size} (${percentageString(list.size, all.size)})"))
}

private fun jmhParams(all: Set<Result>) {
    val hasNoJmhParams = returnCountMap(all, Result::jmhParamCount)[0] ?: 0
    output.add(ResKeyValue("Benchmark has no jmh params", "$hasNoJmhParams (${percentageString(hasNoJmhParams, all.size)})"))

    val jmhParamsCleaned = all.filter { it.jmhParamCount == it.jmhParamCountOwnClass + it.jmhParamCountArgument }
    val jmhParamsFailed = all.filter { it.jmhParamCount != it.jmhParamCountOwnClass + it.jmhParamCountArgument }.size

    val jmhParamsDefinedInClass = jmhParamsCleaned.map { it.jmhParamCountOwnClass }.sum()
    val jmhParamsDefinedExternal = jmhParamsCleaned.map { it.jmhParamCountArgument }.sum()
    val jmhParamsTotal = jmhParamsDefinedInClass + jmhParamsDefinedExternal
    output.add(ResKeyValue("$jmhParamsTotal JmhParams used", "$jmhParamsDefinedInClass (${percentageString(jmhParamsDefinedInClass, jmhParamsTotal)}) defined in own class, $jmhParamsDefinedExternal (${percentageString(jmhParamsDefinedExternal, jmhParamsTotal)}) defined external & $jmhParamsFailed uses own class as function argument"))

}

private fun params(all: Set<Result>) {
    val numberOfStateObjectsWithJmhParams = all.map { it.paramCountStateObject - it.paramCountStateObjectWithoutJmhParam }.sum()
    val numberOfStateObjectsWithoutJmhParams = all.map { it.paramCountStateObjectWithoutJmhParam }.sum()
    val totalStateObjects = all.map { it.paramCountStateObject }.sum()
    output.add(ResKeyValue("$totalStateObjects stateObjects are used", "$numberOfStateObjectsWithJmhParams uses one or more jmhParams and $numberOfStateObjectsWithoutJmhParams has no jmh param"))

    val hasStateObject = all.filter { it.paramCountStateObject > 0 }.size

    output.add(ResKeyValue("state object usage", "$hasStateObject (${percentageString(hasStateObject, all.size)}) benchmarks use a state object"))

    val countBlackhole = all.filter { it.hasBlackhole }.size
    val countControl = all.filter { it.hasControl }.size
    val countBenchmarkParams = all.filter { it.hasBenchmarkParams }.size
    val countIterationParams = all.filter { it.hasIterationParams }.size
    val countThreadParams = all.filter { it.hasThreadParams }.size
    val totalArguments = all.map { it.paramCount }.sum()
    val unresolved = totalArguments - numberOfStateObjectsWithJmhParams - numberOfStateObjectsWithoutJmhParams - countBlackhole - countControl - countBenchmarkParams - countIterationParams - countThreadParams
    output.add(ResKeyValue("$totalArguments method arguments are used", "$unresolved cannot be resolved ${percentageString(unresolved, totalArguments)}"))
}

private fun avgBenchsAndInnerClassBenchs(all: Set<Result>) {
    avgNumberOfBenchmarksPerClass(all, 1)
    avgNumberOfBenchmarksPerClass(all, 5)
    avgNumberOfBenchmarksPerClass(all, 10)
    avgNumberOfBenchmarksPerClass(all, 25)
    avgNumberOfBenchmarksPerClass(all, 50)
    avgNumberOfBenchmarksPerClass(all, 100)
    avgNumberOfBenchmarksPerClass(all, 250)
    avgNumberOfBenchmarksPerClass(all, 500)
    avgNumberOfBenchmarksPerFile(all, 1)
    avgNumberOfBenchmarksPerFile(all, 5)
    avgNumberOfBenchmarksPerFile(all, 10)
    avgNumberOfBenchmarksPerFile(all, 25)
    avgNumberOfBenchmarksPerFile(all, 50)
    avgNumberOfBenchmarksPerFile(all, 100)
    avgNumberOfBenchmarksPerFile(all, 250)
    avgNumberOfBenchmarksPerFile(all, 500)
    val innerClassBenchmark = all.filter { it.className.contains("$") }.size
    output.add(ResKeyValue("benchmark is defined in inner class", "$innerClassBenchmark (${percentageString(innerClassBenchmark, all.size)})"))
}

private fun avgNumberOfBenchmarksPerClass(all: Set<Result>, minBenchs: Int) {
    val filtered = all.groupBy { it.project }.filter { it.value.size >= minBenchs }.values.flatten()
    val numberOfBenchmarks = filtered.size
    val avgNumberOfBenchmarksPerClass = numberOfBenchmarks / filtered.groupBy { it.className }.count().toDouble()
    output.add(ResKeyValue("average number of benchmarks per class (min=$minBenchs)", "$avgNumberOfBenchmarksPerClass"))
}

private fun avgNumberOfBenchmarksPerFile(all: Set<Result>, minBenchs: Int) {
    val filtered = all.groupBy { it.project }.filter { it.value.size >= minBenchs }.values.flatten()
    val numberOfBenchmarks = filtered.size
    val avgNumberOfBenchmarksPerFile = numberOfBenchmarks / filtered.groupBy { it.className.substringBefore("$") }.count().toDouble()
    output.add(ResKeyValue("average number of benchmarks per file (min=$minBenchs)", "$avgNumberOfBenchmarksPerFile"))
}