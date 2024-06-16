package smb.conf.executor

import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class CsvUnitParser(file: File) : CsvParser<Unit>(file, Unit::class.java, true)

open class CsvParser<T>(file: File, private val clazz: Class<T>, private val skipHeader: Boolean) {

    private var fileReader = BufferedReader(FileReader(file))

    private fun getBean(): CsvToBean<T> {
        val mappingStrategy = ColumnPositionMappingStrategy<T>()
        mappingStrategy.type = clazz

        val builder = CsvToBeanBuilder<T>(fileReader)
                .withMappingStrategy(mappingStrategy)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)

        if (skipHeader) {
            builder.withSkipLines(1)
        }

        return builder.build()
    }

    fun getList(): Set<T> {
        val result = getBean().toSet()

        fileReader.close()

        return result
    }
}