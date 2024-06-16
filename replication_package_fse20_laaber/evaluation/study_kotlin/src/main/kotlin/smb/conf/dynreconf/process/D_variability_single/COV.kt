package smb.conf.dynreconf.process.D_variability_single

import smb.conf.dynreconf.utils.CsvLineKey
import smb.conf.dynreconf.utils.CsvResultItem.Companion.header
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import org.openjdk.jmh.reconfigure.statistics.evaluation.CovEvaluation
import org.openjdk.jmh.runner.Defaults.RECONFIGURE_COV_THRESHOLD
import java.io.FileWriter
import java.nio.file.Paths

class COV(outputDir: String) {
    private val outputCov = FileWriter(Paths.get(outputDir, "outputCovSingle.csv").toFile())

    fun generateHeader() {
        outputCov.append(header)
    }

    fun run(key: CsvLineKey, histogram: MutableMap<Int, MutableMap<Int, MutableList<HistogramItem>>>) {
        outputCov.append(key.output())
        evaluation(histogram)
        outputCov.appendln("")
        outputCov.flush()
    }

    private fun evaluation(histogram: MutableMap<Int, MutableMap<Int, MutableList<HistogramItem>>>) {
        histogram.forEach { (_, map) ->
            val evaluation = CovEvaluation.getIterationInstance(RECONFIGURE_COV_THRESHOLD)
            map.forEach { (iteration, list) ->
                if (iteration <= 50) {
                    evaluation.addIteration(list)
                    evaluation.calculateVariability()
                    val currentCov = evaluation.getCovOfIteration(iteration)

                    outputCov.append(";$currentCov")
                }
            }
        }
    }
}