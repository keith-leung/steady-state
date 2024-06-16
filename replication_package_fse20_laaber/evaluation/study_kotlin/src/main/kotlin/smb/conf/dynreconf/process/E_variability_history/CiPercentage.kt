package smb.conf.dynreconf.process.E_variability_history

import smb.conf.dynreconf.utils.CsvResultItem.Companion.header
import smb.conf.dynreconf.utils.CsvResultItemParser
import java.io.FileWriter
import java.nio.file.Paths
import java.util.*

class CiPercentage(outputDir: String) {
    private val input = Paths.get(outputDir, "outputCiSingle.csv").toFile()
    private val output = FileWriter(Paths.get(outputDir, "outputCiHistory.csv").toFile())

    fun run() {
        output.append(header)

        CsvResultItemParser(input).getList().forEach {
            val map = it.getMap()

            output.append(it.getKey().output())
            for (f in 1 until 6) {
                val iterationMap = map.getValue(f)
                output.append(";;;;")
                for (i in 5 until 51) {
                    val width0 = iterationMap.getValue(i)
                    val width1 = iterationMap.getValue(i - 1)
                    val width2 = iterationMap.getValue(i - 2)
                    val width3 = iterationMap.getValue(i - 3)
                    val width4 = iterationMap.getValue(i - 4)
                    val value = Collections.max(mutableListOf(width0, width1, width2, width3, width4))
                    output.append(";$value")
                }
            }
            output.append("\n")
            output.flush()
        }
    }
}