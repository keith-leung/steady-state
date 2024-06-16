package smb.conf.sop.analysis.numberofbenchmarks

import com.opencsv.bean.CsvBindByPosition

class ResNumberOfBenchmarks {
    @CsvBindByPosition(position = 0)
    lateinit var project: String

    @CsvBindByPosition(position = 1)
    var benchmarks: Int = 0

    constructor()
    constructor(project: String, benchmarks: Int) {
        this.project = project
        this.benchmarks = benchmarks
    }
}