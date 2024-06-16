package smb.conf.sop.analysis.executiontime

import ch.uzh.ifi.seal.bencher.JMHVersion
import com.opencsv.bean.CsvBindByPosition

class ResExecutionTime {
    @CsvBindByPosition(position = 0)
    lateinit var project: String

    @CsvBindByPosition(position = 1)
    lateinit var className: String

    @CsvBindByPosition(position = 2)
    lateinit var jmhVersion: JMHVersion

    @CsvBindByPosition(position = 3)
    lateinit var benchmarkName: String

    @CsvBindByPosition(position = 4)
    var executionTimeDefault: Long = 0

    @CsvBindByPosition(position = 5)
    var executionTime: Double = 0.0

    @CsvBindByPosition(position = 6)
    var executionTimePercentage: Double = 0.0

    @CsvBindByPosition(position = 7)
    var warmupTimeDefault: Long = 0

    @CsvBindByPosition(position = 8)
    var warmupTime: Double = 0.0

    @CsvBindByPosition(position = 9)
    var warmupTimePercentage: Double = 0.0

    @CsvBindByPosition(position = 10)
    var measurementTimeDefault: Long = 0

    @CsvBindByPosition(position = 11)
    var measurementTime: Double = 0.0

    @CsvBindByPosition(position = 12)
    var measurementTimePercentage: Double = 0.0

    @CsvBindByPosition(position = 13)
    var onlyModeChanged: Boolean = false

    @CsvBindByPosition(position = 14)
    var onlySingleShot: Boolean = false

    @CsvBindByPosition(position = 15)
    var measurementWarmupRatio: Double = 0.0

    @CsvBindByPosition(position = 16)
    var measurementWarmupRatioPerMeasurementFork: Double = 0.0

    @CsvBindByPosition(position = 17)
    var hasWarmupForks: Boolean = false

    @CsvBindByPosition(position = 18)
    var parameterizationCombinations: Int = 0

    constructor()
    constructor(project: String, className: String, jmhVersion: JMHVersion, benchmarkName: String, executionTimeDefault: Long, executionTime: Double, warmupTimeDefault: Long, warmupTime: Double, measurementTimeDefault: Long, measurementTime: Double, onlyModeChanged: Boolean, onlySingleShot: Boolean, measurementWarmupRatio: Double, measurementWarmupRatioPerMeasurementFork: Double, hasWarmupForks: Boolean, parameterizationCombinations: Int) {
        this.project = project
        this.className = className
        this.jmhVersion = jmhVersion
        this.benchmarkName = benchmarkName
        this.executionTimeDefault = executionTimeDefault
        this.executionTime = executionTime
        this.executionTimePercentage = executionTime / executionTimeDefault.toDouble()
        this.warmupTimeDefault = warmupTimeDefault
        this.warmupTime = warmupTime
        this.warmupTimePercentage = warmupTime / warmupTimeDefault.toDouble()
        this.measurementTimeDefault = measurementTimeDefault
        this.measurementTime = measurementTime
        this.measurementTimePercentage = measurementTime / measurementTimeDefault.toDouble()
        this.onlyModeChanged = onlyModeChanged
        this.onlySingleShot = onlySingleShot
        this.measurementWarmupRatio = measurementWarmupRatio
        this.measurementWarmupRatioPerMeasurementFork = measurementWarmupRatioPerMeasurementFork
        this.hasWarmupForks = hasWarmupForks
        this.parameterizationCombinations = parameterizationCombinations
    }
}