package smb.conf.dynreconf.utils

import org.apache.commons.io.FileUtils
import org.openjdk.jmh.reconfigure.helper.HistogramItem
import java.io.File

fun getHistogramItems(file: File, filter: (fork: Int, iteration: Int) -> Boolean): MutableCollection<HistogramItem> {
    val list = mutableSetOf<HistogramItem>()
    val iterator = FileUtils.lineIterator(file, "UTF-8")
    try {
        while (iterator.hasNext()) {
            val line = iterator.nextLine()
            if (line != "project;commit;benchmark;params;instance;trial;fork;iteration;mode;unit;value_count;value") {
                val parts = line.split(";")
                if (filter(parts[6].toInt(), parts[7].toInt())) {
                    list.add(HistogramItem(parts[6].toInt(), parts[7].toInt(), parts[11].toDouble(), parts[10].toLong()))
                }
            }
        }
    } finally {
        iterator.close()
    }

    return list
}

val untilIteration50 = fun(_: Int, iteration: Int): Boolean {
    return iteration <= 50
}

val untilIteration60 = fun(_: Int, iteration: Int): Boolean {
    return iteration <= 60
}

val all = fun(_: Int, _: Int): Boolean {
    return true
}

val firstFork = fun(fork: Int, _: Int): Boolean {
    return fork == 1
}