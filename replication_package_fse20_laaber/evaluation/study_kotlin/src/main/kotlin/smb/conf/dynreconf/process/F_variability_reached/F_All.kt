package smb.conf.dynreconf.process.F_variability_reached

class F_All(private val outputDir: String) {
    fun run() {
        val cov = COV(outputDir)
        val ci = CiPercentage(outputDir)
        val divergence = Divergence(outputDir)

        cov.run()
        ci.run()
        divergence.run()
    }
}