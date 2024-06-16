package smb.conf.dynreconf.process.H_compare_criteria

import smb.conf.dynreconf.utils.all
import smb.conf.dynreconf.utils.getHistogramItems
import org.openjdk.jmh.reconfigure.helper.HistogramHelper
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import org.openjdk.jmh.reconfigure.helper.OutlierDetector
import org.openjdk.jmh.reconfigure.statistics.ReconfigureConstants.OUTLIER_FACTOR
import org.openjdk.jmh.reconfigure.statistics.Sampler
import org.openjdk.jmh.reconfigure.statistics.ci.CiPercentage
import org.openjdk.jmh.reconfigure.statistics.ci.CiRatio
import java.io.File
import java.io.FileWriter
import java.nio.file.Paths

class Evaluation(private val csvInput: File, private val outputDir: String) {

    private val header = "project;commit;benchmark;params;threshold;f2;f3;f4;f5;reachedForks;reachedF1;reachedF2;reachedF3;reachedF4;reachedF5"

    fun run() {
        val inputCov = Paths.get(outputDir, "outputCovTotal.csv").toFile()
        val inputCi = Paths.get(outputDir, "outputCiTotal.csv").toFile()
        val inputDivergence = Paths.get(outputDir, "outputDivergenceTotal.csv").toFile()
        val output = FileWriter(Paths.get(outputDir, "variability.csv").toFile())

        output.append("project;commit;benchmark;params;meanDefault;meanCov;meanCi;meanKld;ciPercentageDefault;ciPercentageCov;ciPercentageCi;ciPercentageKld;effectSizeCov;effectSizeCi;effectSizeKld;wilcoxonCov;wilcoxonCi;wilcoxonKld;ratioMeanCov;ratioLowerCov;ratioUpperCov;ratioMeanCi;ratioLowerCi;ratioUpperCi;ratioMeanKld;ratioLowerKld;ratioUpperKld\n")

        val reachedForkCov = mutableMapOf<String, Int>()
        val reachedIterationCov = mutableMapOf<String, MutableMap<Int, Int>>()
        val reachedForkCi = mutableMapOf<String, Int>()
        val reachedIterationCi = mutableMapOf<String, MutableMap<Int, Int>>()
        val reachedForkDivergence = mutableMapOf<String, Int>()
        val reachedIterationDivergence = mutableMapOf<String, MutableMap<Int, Int>>()

        parseInput(inputCov, reachedForkCov, reachedIterationCov)
        parseInput(inputCi, reachedForkCi, reachedIterationCi)
        parseInput(inputDivergence, reachedForkDivergence, reachedIterationDivergence)

        csvInput.walk().forEach {
            if (it.isFile) {
                try {
                    val name = it.nameWithoutExtension
                    val all = getHistogramItems(it, all)

                    val (project, benchmark, params) = name.split("#")

                    output.append("$project;;$benchmark;$params")

                    // construct dynamic stopping dataset
                    val defaultItems = all.filter { it.iteration > 50 }
                    val covItems = getItems(all, reachedForkCov.getValue(name), reachedIterationCov.getValue(name))
                    val ciItems = getItems(all, reachedForkCi.getValue(name), reachedIterationCi.getValue(name))
                    val kldItems = getItems(all, reachedForkDivergence.getValue(name), reachedIterationDivergence.getValue(name))

                    // sample
                    val defaultSample = Sampler(defaultItems).getSample(10000)
                    val covSample = Sampler(covItems).getSample(10000)
                    val ciSample = Sampler(ciItems).getSample(10000)
                    val kldSample = Sampler(kldItems).getSample(10000)

                    // outlier detector
                    val odDefault = OutlierDetector(OUTLIER_FACTOR, defaultSample)
                    val odCov = OutlierDetector(OUTLIER_FACTOR, covSample)
                    val odCi = OutlierDetector(OUTLIER_FACTOR, ciSample)
                    val odKld = OutlierDetector(OUTLIER_FACTOR, kldSample)
                    odDefault.run()
                    odCov.run()
                    odCi.run()
                    odKld.run()

                    val defaultSampleDouble = HistogramHelper.toArray(odDefault.inlier)
                    val covSampleDouble = HistogramHelper.toArray(odCov.inlier)
                    val ciSampleDouble = HistogramHelper.toArray(odCi.inlier)
                    val kldSampleDouble = HistogramHelper.toArray(odKld.inlier)

                    output.append(";${defaultSampleDouble.average()};${covSampleDouble.average()};${ciSampleDouble.average()};${kldSampleDouble.average()}")

                    val ciDefault = ci(odDefault.inlier)
                    val ciCov = ci(odCov.inlier)
                    val ciCi = ci(odCi.inlier)
                    val ciKld = ci(odKld.inlier)

                    output.append(";$ciDefault;$ciCov;$ciCi;$ciKld")

                    val effectSizeCov = CliffDeltaTest(defaultSampleDouble, covSampleDouble).effectSize
                    val effectSizeCi = CliffDeltaTest(defaultSampleDouble, ciSampleDouble).effectSize
                    val effectSizeKld = CliffDeltaTest(defaultSampleDouble, kldSampleDouble).effectSize

                    output.append(";${Math.abs(effectSizeCov)};${Math.abs(effectSizeCi)};${Math.abs(effectSizeKld)}")

                    val wilcoxonCov = WilcoxonRankSumTest(defaultSampleDouble, covSampleDouble).pValue
                    val wilcoxonCi = WilcoxonRankSumTest(defaultSampleDouble, ciSampleDouble).pValue
                    val wilcoxonKld = WilcoxonRankSumTest(defaultSampleDouble, kldSampleDouble).pValue

                    output.append(";$wilcoxonCov;$wilcoxonCi;$wilcoxonKld")

                    val ciRatioCov = CiRatio(odDefault.inlier, odCov.inlier, 10000, 0.01, "mean", -1)
                    val ciRatioCi = CiRatio(odDefault.inlier, odCi.inlier, 10000, 0.01, "mean", -1)
                    val ciRatioKld = CiRatio(odDefault.inlier, odKld.inlier, 10000, 0.01, "mean", -1)
                    ciRatioCov.run()
                    ciRatioCi.run()
                    ciRatioKld.run()

                    output.append(";${ciRatioCov.statisticMetric};${ciRatioCov.lower};${ciRatioCov.upper};${ciRatioCi.statisticMetric};${ciRatioCi.lower};${ciRatioCi.upper};${ciRatioKld.statisticMetric};${ciRatioKld.lower};${ciRatioKld.upper}")
                    output.append("\n")
                    output.flush()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getItems(items: Collection<HistogramItem>, fork: Int, iterations: Map<Int, Int>): List<HistogramItem> {
        val all = mutableListOf<HistogramItem>()
        iterations.forEach { f, reached ->
            if (f <= fork) {
                var reachedFixed = reached
                if (reachedFixed > 50) {
                    reachedFixed = 50
                }
                val filter = items.filter { it.fork == f && it.iteration > reachedFixed && it.iteration <= reachedFixed + 10 }
                all.addAll(filter)
            }
        }
        return all
    }

    private fun ci(items: List<HistogramItem>): Double {
        return CiPercentage(items, 10000).value
    }

    private fun parseInput(input: File, reachedFork: MutableMap<String, Int>, reachedIteration: MutableMap<String, MutableMap<Int, Int>>) {
        input.forEachLine {
            if (it == header) {
                return@forEachLine
            }

            val parts = it.split(";")
            val name = "${parts[0]}#${parts[2]}#${parts[3]}"
            reachedFork[name] = parts[9].toInt()
            reachedIteration[name] = mutableMapOf(
                    1 to parts[10].toInt(),
                    2 to parts[11].toInt(),
                    3 to parts[12].toInt(),
                    4 to parts[13].toInt(),
                    5 to parts[14].toInt()
            )
        }
    }
}