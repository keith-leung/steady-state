package smb.conf.dynreconf.process.E_variability_history

import smb.conf.dynreconf.utils.CsvResultItem
import smb.conf.dynreconf.utils.CsvResultItemParser
import java.io.FileWriter
import java.nio.file.Paths

class Divergence(outputDir: String) {
    private val input = Paths.get(outputDir, "outputDivergenceSingle.csv").toFile()
    private val output = FileWriter(Paths.get(outputDir, "outputDivergenceHistory.csv").toFile())

    fun run() {
        output.append(CsvResultItem.header)

        CsvResultItemParser(input).getList().forEach {
            val map = it.getMap()

            output.append(it.getKey().output())
            for (f in 1 until 6) {
                val iterationMap = map.getValue(f)
                output.append(";;;;;")
                for (i in 6 until 51) {
                    val delta0 = iterationMap.getValue(i)
                    val delta1 = iterationMap.getValue(i - 1)
                    val delta2 = iterationMap.getValue(i - 2)
                    val delta3 = iterationMap.getValue(i - 3)
                    val delta4 = iterationMap.getValue(i - 4)
                    val value = mutableListOf(delta0, delta1, delta2, delta3, delta4).average()
                    output.append(";$value")
                }
            }

            output.append("\n")
            output.flush()
        }
    }
}