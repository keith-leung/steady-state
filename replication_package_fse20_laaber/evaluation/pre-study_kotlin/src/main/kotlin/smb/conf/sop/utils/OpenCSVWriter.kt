package smb.conf.sop.utils

import com.opencsv.CSVWriter
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.StatefulBeanToCsvBuilder

import java.nio.file.Files
import java.nio.file.Path

object OpenCSVWriter {
    inline fun <reified T> write(outputFile: Path, input: Iterable<T>, mapping: ColumnPositionMappingStrategy<T>? = null) {
        if (!outputFile.toFile().exists()) {
            outputFile.toFile().createNewFile()
        }

        Files.newBufferedWriter(outputFile).use { writer ->
            val builder = StatefulBeanToCsvBuilder<T>(writer)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)

            if (mapping != null) {
                builder.withMappingStrategy(mapping)
            }

            val beanToCsv = builder.build()

            beanToCsv.write(input.toList())
        }
    }
}