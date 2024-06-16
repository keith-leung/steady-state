package smb.conf.dynreconf.analysis.variability

import smb.conf.dynreconf.EvaluationPath
import java.io.File
import java.io.FileWriter
import kotlin.math.abs

private val outputFile = File(EvaluationPath.get("study_results", "variability", "variability.csv"))
private val output = FileWriter(outputFile)

fun main() {
    val file = File(EvaluationPath.get("study_results", "variability"))

    output.appendln("project;benchmarks;meanChangeRateCov;meanChangeRateCi;meanChangeRateDivergence;CiWidthSmallDefault;combinationSignificantCov;combinationSignificantCi;combinationSignificantDivergence;ciRatioSignificantCov;ciRatioSignificantCi;ciRatioSignificantDivergence")

    file.walk().forEach {
        if (it.isFile && it.name == "variability.csv" && it.parent != file.absolutePath) {
            val name = it.parentFile.name
            eval(name, it)
        }
    }

    output.flush()
}

private fun eval(name: String, file: File) {
    var counter = 0.0

    val meanChangeRateCov = mutableListOf<Double>()
    val meanChangeRateCi = mutableListOf<Double>()
    val meanChangeRateDivergence = mutableListOf<Double>()

    var ciWidthSmallerDefault = 0

    var combinationCov = 0
    var combinationCi = 0
    var combinationDivergence = 0

    val ciRatioListCov = mutableListOf<Double>()
    val ciRatioListCi = mutableListOf<Double>()
    val ciRatioListDivergence = mutableListOf<Double>()

    file.forEachLine {
        if (it == "project;commit;benchmark;params;meanDefault;meanCov;meanCi;meanKld;ciPercentageDefault;ciPercentageCov;ciPercentageCi;ciPercentageKld;effectSizeCov;effectSizeCi;effectSizeKld;wilcoxonCov;wilcoxonCi;wilcoxonKld;ratioMeanCov;ratioLowerCov;ratioUpperCov;ratioMeanCi;ratioLowerCi;ratioUpperCi;ratioMeanKld;ratioLowerKld;ratioUpperKld") {
            return@forEachLine
        }

        val parts = it.split(";")
        val meanDefault = parts[4].toDouble()
        val meanCov = parts[5].toDouble()
        val meanCi = parts[6].toDouble()
        val meanKld = parts[7].toDouble()
        val ciPercentageDefault = parts[8].toDouble()
        val ciPercentageCov = parts[9].toDouble()
        val ciPercentageCi = parts[10].toDouble()
        val ciPercentageKld = parts[11].toDouble()
        val effectSizeCov = parts[12].toDouble()
        val effectSizeCi = parts[13].toDouble()
        val effectSizeKld = parts[14].toDouble()
        val wilcoxonCov = parts[15].toDouble()
        val wilcoxonCi = parts[16].toDouble()
        val wilcoxonKld = parts[17].toDouble()
        val ratioMeanCov = parts[18].toDouble()
        val ratioLowerCov = parts[19].toDouble()
        val ratioUpperCov = parts[20].toDouble()
        val ratioMeanCi = parts[21].toDouble()
        val ratioLowerCi = parts[22].toDouble()
        val ratioUpperCi = parts[23].toDouble()
        val ratioMeanKld = parts[24].toDouble()
        val ratioLowerKld = parts[25].toDouble()
        val ratioUpperKld = parts[26].toDouble()

        meanChangeRateCov.add(meanChangeRate(meanDefault, meanCov))
        meanChangeRateCi.add(meanChangeRate(meanDefault, meanCi))
        meanChangeRateDivergence.add(meanChangeRate(meanDefault, meanKld))

        val ciPrecentageThreshold = 0.03
        if (ciPercentageDefault < ciPrecentageThreshold) {
            ciWidthSmallerDefault++
        }

        val effectSizeThreshold = 0.147
        val pvalue = 0.01
        if (effectSizeCov > effectSizeThreshold && wilcoxonCov < pvalue) {
            combinationCov++
        }
        if (effectSizeCi > effectSizeThreshold && wilcoxonCi < pvalue) {
            combinationCi++
        }
        if (effectSizeKld > effectSizeThreshold && wilcoxonKld < pvalue) {
            combinationDivergence++
        }

        if(ratioMeanCov.isNaN()){
            ciRatioListCov.add(0.0)
        }else{
            ciRatioListCov.add(abs( 1 - ratioMeanCov))
        }
        if(ratioMeanCi.isNaN()){
            ciRatioListCi.add(0.0)
        }else{
            ciRatioListCi.add(abs( 1 - ratioMeanCi))
        }
        if(ratioMeanKld.isNaN()){
            ciRatioListDivergence.add(0.0)
        }else{
            ciRatioListDivergence.add(abs( 1 - ratioMeanKld))
        }

        counter++
    }

    output.append("$name;$counter")
    output.append(";${meanChangeRateCov.average()};${meanChangeRateCi.average()};${meanChangeRateDivergence.average()}")
    output.append(";${ciWidthSmallerDefault / counter}")
    output.append(";${combinationCov / counter};${combinationCi / counter};${combinationDivergence / counter}")
    output.appendln(";${ciRatioListCov.average()};${ciRatioListCi.average()};${ciRatioListDivergence.average()}")
}


private fun meanChangeRate(before: Double, after: Double): Double {
    return if ((after / before).isNaN()) {
        0.0
    } else {
        abs((after / before) - 1)
    }
}