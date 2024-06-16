package smb.conf.sop.utils

import ch.uzh.ifi.seal.bencher.JMHVersion
import smb.conf.sop.evaluation.Status
import com.opencsv.bean.AbstractCsvConverter
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.CsvConverter
import java.lang.reflect.Field


class CustomColumnPositionMappingStrategy<T> : ColumnPositionMappingStrategy<T>() {
    override fun determineConverter(field: Field?, elementType: Class<*>?, locale: String?, customConverter: Class<out AbstractCsvConverter>?): CsvConverter {
        if (elementType == JMHVersion::class.java) {
            return ConverterJmhVersion()
        } else if (elementType == Status::class.java) {
            return ConvertStatus()
        } else {
            return super.determineConverter(field, elementType, locale, customConverter)
        }
    }
}

private class ConverterJmhVersion : AbstractCsvConverter() {
    override fun convertToRead(value: String?): Any? {
        return if (value == null || value.isNullOrBlank()) {
            null
        } else {
            val list = value.split(".")
            val major = list[0].toInt()
            val minor = list[1].toInt()
            val patch = list.getOrNull(2)?.toInt() ?: 0
            JMHVersion(major, minor, patch)
        }
    }
}

private class ConvertStatus : AbstractCsvConverter() {
    override fun convertToRead(value: String?): Any? {
        return when (value) {
            Status.BOTH_SET.name -> Status.BOTH_SET
            Status.BOTH_UNSET.name -> Status.BOTH_UNSET
            Status.TIME_SET.name -> Status.TIME_SET
            Status.UNIT_SET.name -> Status.UNIT_SET
            else -> null
        }
    }
}