package smb.conf.dynreconf.process.H_compare_criteria

import org.hipparchus.distribution.continuous.NormalDistribution
import org.hipparchus.stat.inference.MannWhitneyUTest

// also called Mann Whitney U test
class WilcoxonRankSumTest(testGroup: List<Double>, controlGroup: List<Double>) {
    private val uMin: Double
    val zScore: Double
    // Two-tailed p-value
    val pValue: Double

    init {
        val testGroupSize = testGroup.size.toDouble()
        val controlGroupSize = controlGroup.size.toDouble()

        uMin = MannWhitneyUTest().mannWhitneyU(testGroup.toDoubleArray(), controlGroup.toDoubleArray())
        zScore = (uMin - testGroupSize * controlGroupSize / 2.0) / Math.sqrt(testGroupSize * controlGroupSize * (testGroupSize + controlGroupSize + 1) / 12.0)

        // Two-tailed p-value
        pValue = NormalDistribution().cumulativeProbability(zScore) * 2
    }

    fun regressionDetected(significanceLevel: Double): Boolean {
        return pValue < significanceLevel
    }
}