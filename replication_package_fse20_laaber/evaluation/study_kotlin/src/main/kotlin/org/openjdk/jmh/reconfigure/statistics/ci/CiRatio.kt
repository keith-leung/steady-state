package org.openjdk.jmh.reconfigure.statistics.ci

import org.apache.commons.io.IOUtils
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import java.io.IOException
import java.nio.charset.Charset

class CiRatio(private val histogramList1: List<HistogramItem>, private val histogramList2: List<HistogramItem>, bootstrapSimulations: Int, significanceLevel: Double, statistic: String, ciInvocationSamples: Int) : CI(listOf(), bootstrapSimulations, significanceLevel, statistic, ciInvocationSamples) {

    override fun run() {
        val file1 = getTmpFile(histogramList1)
        val file2 = getTmpFile(histogramList2)
        try {
            val process = Runtime.getRuntime().exec("$paToolPath -om -bs $bootstrapSimulations -is $ciInvocationSamples -sig $significanceLevel -st $statistic $file1 $file2")
            val inputString = IOUtils.toString(process.inputStream, Charset.defaultCharset())
            val errorString = IOUtils.toString(process.errorStream, Charset.defaultCharset())
            val output = (inputString + "\n" + errorString).trim { it <= ' ' }
            val line = getFirstLine(output)
            val parts = line!!.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            statisticMetric = parts[11].toDouble()
            lower = parts[12].toDouble()
            upper = parts[13].toDouble()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}