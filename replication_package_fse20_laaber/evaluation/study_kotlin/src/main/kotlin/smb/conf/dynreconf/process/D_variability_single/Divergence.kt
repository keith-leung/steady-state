package smb.conf.dynreconf.process.D_variability_single

import smb.conf.dynreconf.utils.CsvLineKey
import smb.conf.dynreconf.utils.CsvResultItem.Companion.header
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import org.openjdk.jmh.reconfigure.statistics.evaluation.DivergenceEvaluation
import org.openjdk.jmh.runner.Defaults.RECONFIGURE_KLD_THRESHOLD
import java.io.FileWriter
import java.nio.file.Paths

class Divergence(outputDir: String) {
    private val outputDivergence = FileWriter(Paths.get(outputDir, "outputDivergenceSingle.csv").toFile())

    fun generateHeader() {
        outputDivergence.append(header)
    }

    fun run(key: CsvLineKey, histogram: MutableMap<Int, MutableMap<Int, MutableList<HistogramItem>>>) {
        outputDivergence.append(key.output())
        evaluation(histogram)
        outputDivergence.appendln("")
        outputDivergence.flush()
    }

    private fun evaluation(histogram: MutableMap<Int, MutableMap<Int, MutableList<HistogramItem>>>) {
        histogram.forEach { (_, map) ->
            val evaluation = DivergenceEvaluation.getIterationInstance(RECONFIGURE_KLD_THRESHOLD)
            map.forEach { (iteration, list) ->
                if (iteration <= 50) {
                    evaluation.addIteration(list)
                    evaluation.calculateVariability()
                    val currentPValue = evaluation.getPValueOfIteration(iteration)

                    if (currentPValue == null) {
                        outputDivergence.append(";")
                    } else {
                        outputDivergence.append(";$currentPValue")
                    }
                }
            }
        }
    }
}