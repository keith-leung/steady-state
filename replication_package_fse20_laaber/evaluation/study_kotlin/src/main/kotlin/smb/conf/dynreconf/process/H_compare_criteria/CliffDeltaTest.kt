package smb.conf.dynreconf.process.H_compare_criteria

class CliffDeltaTest(testGroup: List<Double>, controlGroup: List<Double>) {
    val effectSize: Double

    init {
        var testGreaterThanControl = 0
        var testLessThanControl = 0

        for (testItem in testGroup) {
            for (controlItem in controlGroup) {
                if (testItem > controlItem) {
                    testGreaterThanControl++
                } else if (testItem < controlItem) {
                    testLessThanControl++
                }
            }
        }

        effectSize = (testGreaterThanControl - testLessThanControl) / (testGroup.size * controlGroup.size).toDouble()
    }

    fun regressionDetected(threshold: Double): Boolean {
        return Math.abs(effectSize) > threshold
    }
}