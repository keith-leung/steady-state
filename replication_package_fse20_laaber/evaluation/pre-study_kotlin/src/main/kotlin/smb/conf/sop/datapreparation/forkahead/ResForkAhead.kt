package smb.conf.sop.datapreparation.forkahead

import com.opencsv.bean.CsvBindByPosition

class ResForkAhead {
    @CsvBindByPosition(position = 0)
    lateinit var project: String

    @CsvBindByPosition(position = 1)
    var computationFailed: Boolean = false

    @CsvBindByPosition(position = 2)
    var aheadBy: Int? = null

    @CsvBindByPosition(position = 3)
    var behindBy: Int? = null

    @CsvBindByPosition(position = 4)
    var nothingChanged: Boolean? = null

    constructor()

    constructor(project: String, computationFailed: Boolean) {
        this.project = project
        this.computationFailed = computationFailed
    }

    constructor(project: String, computationFailed: Boolean, aheadBy: Int?, behindBy: Int?, nothingChanged: Boolean?) {
        this.project = project
        this.computationFailed = computationFailed
        this.aheadBy = aheadBy
        this.behindBy = behindBy
        this.nothingChanged = nothingChanged
    }
}