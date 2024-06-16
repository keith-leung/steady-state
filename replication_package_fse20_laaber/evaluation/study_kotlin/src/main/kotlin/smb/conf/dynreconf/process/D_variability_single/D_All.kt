package smb.conf.dynreconf.process.D_variability_single

import smb.conf.dynreconf.utils.CsvLineKey
import smb.conf.dynreconf.utils.getHistogramItems
import smb.conf.dynreconf.utils.untilIteration50
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import java.io.File

class D_All(private val csvInput: File, private val outputDir: String) {
    fun run() {
        val cov = COV(outputDir)
        val ci = CiPercentage(outputDir)
        val divergence = Divergence(outputDir)

        cov.generateHeader()
        ci.generateHeader()
        divergence.generateHeader()

        csvInput.walk().forEach {
            if (it.isFile) {
                val (project, benchmark, params) = it.nameWithoutExtension.split("#")
                val key = CsvLineKey(project, "", benchmark, params)
                val list = getHistogramItems(it, untilIteration50)

                val histogram = mutableMapOf<Int, MutableMap<Int, MutableList<HistogramItem>>>()
                list.forEach { item ->
                    if (histogram[item.fork] == null) {
                        histogram[item.fork] = mutableMapOf()
                    }

                    if (histogram.getValue(item.fork)[item.iteration] == null) {
                        histogram.getValue(item.fork)[item.iteration] = mutableListOf()
                    }

                    val iterationList = histogram.getValue(item.fork).getValue(item.iteration)
                    iterationList.add(item)
                }

                cov.run(key, histogram)
                ci.run(key, histogram)
                divergence.run(key, histogram)
                println("D: ${it.absolutePath} processed")
            }
        }
    }
}

