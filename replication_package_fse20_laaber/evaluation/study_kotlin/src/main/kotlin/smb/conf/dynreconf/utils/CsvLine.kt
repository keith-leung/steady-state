package smb.conf.dynreconf.utils

import com.opencsv.bean.CsvBindByPosition
import org.openjdk.jmh.reconfigure.helper.HistogramItem

class CsvLine {
    @CsvBindByPosition(position = 0)
    lateinit var project: String

    @CsvBindByPosition(position = 1)
    lateinit var commit: String

    @CsvBindByPosition(position = 2)
    lateinit var benchmark: String

    @CsvBindByPosition(position = 3)
    lateinit var params: String

    @CsvBindByPosition(position = 4)
    lateinit var instance: String

    @CsvBindByPosition(position = 5)
    var trial: Int = 0

    @CsvBindByPosition(position = 6)
    var fork: Int = 0

    @CsvBindByPosition(position = 7)
    var iteration: Int = 0

    @CsvBindByPosition(position = 8)
    lateinit var mode: String

    @CsvBindByPosition(position = 9)
    lateinit var unit: String

    @CsvBindByPosition(position = 10)
    var value_count: Long = 0

    @CsvBindByPosition(position = 11)
    var value: Double = 0.0

    constructor(project: String, commit: String, benchmark: String, params: String, instance: String, trial: Int, fork: Int, iteration: Int, mode: String, unit: String, value_count: Long, value: Double) {
        this.project = project
        this.commit = commit
        this.benchmark = benchmark
        this.params = params
        this.instance = instance
        this.trial = trial
        this.fork = fork
        this.iteration = iteration
        this.mode = mode
        this.unit = unit
        this.value_count = value_count
        this.value = value
    }

    constructor()

    fun getKey(): CsvLineKey {
        return CsvLineKey(project, commit, benchmark, params)
    }

    fun getHistogramItem(): HistogramItem {
        return HistogramItem(fork, iteration, value, value_count)
    }
}