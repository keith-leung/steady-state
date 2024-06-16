package smb.conf.executor

import com.opencsv.bean.CsvBindByPosition

class Unit {
    @CsvBindByPosition(position = 0)
    var project: String = ""

    @CsvBindByPosition(position = 1)
    var benchmark: String = ""

    @CsvBindByPosition(position = 2)
    var params: String = ""

    @CsvBindByPosition(position = 3)
    var javaPath: String = ""

    @CsvBindByPosition(position = 4)
    var jarFile: String = ""

    @CsvBindByPosition(position = 5)
    var arguments: String = ""

    constructor(
        project: String,
        benchmark: String,
        params: String,
        javaPath: String,
        jarFile: String,
        arguments: String
    ) {
        this.project = project
        this.benchmark = benchmark
        this.params = params
        this.javaPath = javaPath
        this.jarFile = jarFile
        this.arguments = arguments
    }

    constructor()
}