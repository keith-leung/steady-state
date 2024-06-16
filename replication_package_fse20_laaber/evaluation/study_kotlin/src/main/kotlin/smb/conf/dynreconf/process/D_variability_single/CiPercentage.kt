package smb.conf.dynreconf.process.D_variability_single

import smb.conf.dynreconf.utils.CsvLineKey
import smb.conf.dynreconf.utils.CsvResultItem.Companion.header
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import org.openjdk.jmh.reconfigure.statistics.evaluation.CiPercentageEvaluation
import org.openjdk.jmh.runner.Defaults
import java.io.File
import java.io.FileWriter
import java.nio.file.Paths

class CiPercentage(outputDir: String) {
    private val outputCi = FileWriter(Paths.get(outputDir, "outputCiSingle.csv").toFile())

    fun generateHeader() {
        outputCi.append(header)
    }

    fun run(key: CsvLineKey, histogram: MutableMap<Int, MutableMap<Int, MutableList<HistogramItem>>>) {
        outputCi.append(key.output())
        evaluation(histogram)
        outputCi.appendln("")
        outputCi.flush()
    }

    private fun evaluation(histogram: MutableMap<Int, MutableMap<Int, MutableList<HistogramItem>>>) {
        histogram.forEach { (_, map) ->
            val evaluation = CiPercentageEvaluation.getIterationInstance(Defaults.RECONFIGURE_CI_THRESHOLD)
            map.forEach { (iteration, list) ->
                if (iteration <= 50) {
                    evaluation.addIteration(list)
                    evaluation.calculateVariability()
                    val currentCiPercentage = evaluation.getCiPercentageOfIteration(iteration)

                    outputCi.append(";$currentCiPercentage")
                    deleteTmp()
                }
            }
        }
    }

    private fun deleteTmp() {
        val tmp = File(System.getProperty("java.io.tmpdir"))
        tmp.listFiles().forEach {
            if (it.name.startsWith("reconfigure")) {
                it.delete()
            }
        }
    }
}