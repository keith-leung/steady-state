package smb.conf.sop.utils

import com.opencsv.bean.ColumnPositionMappingStrategy

class CustomMappingStrategy<T>(type: Class<T>) : ColumnPositionMappingStrategy<T>() {

    init {
        setType(type)
    }

    override fun generateHeader(bean: T): Array<String> {
        super.generateHeader(bean)
        val numColumns = findMaxFieldIndex()
        return (0..numColumns).map {
            findField(it).field.name
        }.toTypedArray()
    }
}