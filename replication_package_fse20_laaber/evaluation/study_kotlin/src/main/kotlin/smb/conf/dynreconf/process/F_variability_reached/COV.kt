package smb.conf.dynreconf.process.F_variability_reached

import smb.conf.dynreconf.utils.CsvResultItemParser
import java.io.FileWriter
import java.nio.file.Paths

class COV(outputDir: String, private val threshold: Double = 0.01) {
    private val input = Paths.get(outputDir, "outputCovHistory.csv").toFile()
    private val output = FileWriter(Paths.get(outputDir, "outputCovReached.csv").toFile())

    fun run() {
        output.append("project;commit;benchmark;params;f1;f2;f3;f4;f5\n")
        CsvResultItemParser(input).getList().forEach {
            val map = it.getMap()

            output.append(it.getKey().output())
            for (f in 1 until 6) {
                var reached = Int.MAX_VALUE
                val iterationMap = map.getValue(f)
                for (i in 5 until 51) {
                    val value = iterationMap.getValue(i)
                    if (value < threshold) {
                        reached = i
                        break
                    }
                }
                output.append(";" + reached)
            }
            output.append("\n")
            output.flush()
        }
    }
}