package smb.conf.sop.analysis.features

import com.opencsv.bean.CsvBindByPosition

class ResKeyValue {
    @CsvBindByPosition(position = 0)
    lateinit var key: String

    @CsvBindByPosition(position = 1)
    var value: String? = null

    constructor()
    constructor(key: String, value: String?) {
        this.key = key
        this.value = value
    }
}