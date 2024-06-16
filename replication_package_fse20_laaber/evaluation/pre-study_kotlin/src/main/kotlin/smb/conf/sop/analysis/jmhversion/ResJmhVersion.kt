package smb.conf.sop.analysis.jmhversion

import ch.uzh.ifi.seal.bencher.JMHVersion
import com.opencsv.bean.CsvBindByPosition

class ResJmhVersion {
    @CsvBindByPosition(position = 0)
    lateinit var version: JMHVersion

    @CsvBindByPosition(position = 1)
    var count: Int = 0

    @CsvBindByPosition(position = 2)
    var countShortLived: Int = 0

    @CsvBindByPosition(position = 3)
    var countLongLived: Int = 0

    constructor()
    constructor(version: JMHVersion, count: Int, countShortLived: Int, countLongLived: Int) {
        this.version = version
        this.count = count
        this.countShortLived = countShortLived
        this.countLongLived = countLongLived
    }
}