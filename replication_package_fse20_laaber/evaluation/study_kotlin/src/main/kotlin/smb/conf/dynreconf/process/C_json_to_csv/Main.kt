package smb.conf.dynreconf.process.C_json_to_csv

import ch.uzh.ifi.seal.bencher.jmhResults.JMHResultTransformer
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Needed arguments: inputFolder (where JMH JSON files are) outputFolder (where CSV files should be saved")
        exitProcess(-1)
    }

    val inputFolder = File(args[0])
    val outputFolder = File(args[1])
    inputFolder.walk().forEach { inputFile ->
        if (inputFile.isFile && inputFile.extension.toLowerCase() == "json") {
            val outputFile = Paths.get(outputFolder.absolutePath, inputFile.nameWithoutExtension + ".csv").toFile()

            val parser = JMHResultTransformer(inStream = FileInputStream(inputFile), outStream = FileOutputStream(outputFile), trial = 0, commit = "", project = "", instance = "")
            val error = parser.execute()
            if (error.isDefined()) {
                throw RuntimeException("Execution failed with '${error.get()}'")
            }
        }
    }
}
