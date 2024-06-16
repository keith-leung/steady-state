package smb.conf.dynreconf.process

import smb.conf.dynreconf.process.D_variability_single.D_All
import smb.conf.dynreconf.process.E_variability_history.E_All
import smb.conf.dynreconf.process.F_variability_reached.F_All
import smb.conf.dynreconf.process.G_variability_total.G_All
import smb.conf.dynreconf.process.H_compare_criteria.Evaluation
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Needed arguments: inputFolder (where JMH CSV files are) outputFolder (study_results/variability/[projectName])")
        exitProcess(-1)
    }

    val csvInput = File(args[0])
    val outputDir = args[1]

    val d = D_All(csvInput, outputDir)
    d.run()
    println("End step D")

    val e = E_All(outputDir)
    e.run()
    println("End step E")

    val f = F_All(outputDir)
    f.run()
    println("End step F")

    val g = G_All(csvInput.absolutePath, outputDir)
    g.run()
    println("End step G")

    val h = Evaluation(csvInput, outputDir)
    h.run()
    println("End step H")
}