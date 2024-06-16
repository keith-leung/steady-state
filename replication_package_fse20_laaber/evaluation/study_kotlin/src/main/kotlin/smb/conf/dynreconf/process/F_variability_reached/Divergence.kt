package smb.conf.dynreconf.process.F_variability_reached

import smb.conf.dynreconf.utils.CsvResultItemParser
import java.io.FileWriter
import java.nio.file.Paths

class Divergence(outputDir: String, private val threshold: Double = 0.99) {
    private val input = Paths.get(outputDir, "outputDivergenceHistory.csv").toFile()
    private val output = FileWriter(Paths.get(outputDir, "outputDivergenceReached.csv").toFile())

    fun run() {
        output.append("project;commit;benchmark;params;f1;f2;f3;f4;f5\n")
        CsvResultItemParser(input).getList().forEach {
            val map = it.getMap()

            output.append(it.getKey().output())
            for (f in 1 until 6) {
                var reached = Int.MAX_VALUE
                val iterationMap = map.getValue(f)
                for (i in 6 until 51) {
                    val value = iterationMap.getValue(i)
                    if (value > threshold) {
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