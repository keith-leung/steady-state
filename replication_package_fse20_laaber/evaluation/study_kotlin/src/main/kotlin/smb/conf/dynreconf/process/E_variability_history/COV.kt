package smb.conf.dynreconf.process.E_variability_history

import smb.conf.dynreconf.utils.CsvResultItem.Companion.header
import smb.conf.dynreconf.utils.CsvResultItemParser
import java.io.FileWriter
import java.nio.file.Paths
import java.util.*

class COV(outputDir: String) {
    private val input = Paths.get(outputDir, "outputCovSingle.csv").toFile()
    private val output = FileWriter(Paths.get(outputDir, "outputCovHistory.csv").toFile())

    fun run() {
        output.append(header)

        CsvResultItemParser(input).getList().forEach {
            val map = it.getMap()

            output.append(it.getKey().output())
            for (f in 1 until 6) {
                val iterationMap = map.getValue(f)
                output.append(";;;;")
                for (i in 5 until 51) {
                    val current = iterationMap.getValue(i)
                    val delta1 = iterationMap.getValue(i - 1) - current
                    val delta2 = iterationMap.getValue(i - 2) - current
                    val delta3 = iterationMap.getValue(i - 3) - current
                    val delta4 = iterationMap.getValue(i - 4) - current
                    val value = Collections.max(mutableListOf(delta1, delta2, delta3, delta4))
                    output.append(";$value")
                }
            }
            output.append("\n")
            output.flush()
        }
    }
}