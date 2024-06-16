package smb.conf.dynreconf.process.G_variability_total

class G_All(private val csvInput: String, private val outputDir: String) {
    fun run() {
        val cov = COV(csvInput, outputDir)
        val ci = CiPercentage(csvInput, outputDir)
        val divergence = Divergence(csvInput, outputDir)

        cov.run()
        ci.run()
        divergence.run()
    }
}