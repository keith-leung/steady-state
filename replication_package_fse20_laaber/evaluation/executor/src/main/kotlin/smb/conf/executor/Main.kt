package smb.conf.executor

import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.FileWriter
import java.nio.file.Paths
import kotlin.system.exitProcess

private val log = LogManager.getLogger()
private const val constantArguments = "-tu s -rf json"

fun main(args: Array<String>) {
    if(args.size != 2){
        log.error("Needed arguments: inputFile outputDir")
        exitProcess(-1)
    }

    val inputFile = File(args[0])
    val outputDir = args[1]

    val output = FileWriter(Paths.get(outputDir, "executionTime.csv").toFile())

    val units = CsvUnitParser(inputFile).getList()

    units.forEach {
        log.debug("Executing benchmark ${it.benchmark} with the JMH params ${it.params} from project ${it.project}")

        val jarFileAbsolute = it.jarFile.replace("~", System.getProperty("user.home"))
        val javaPathAbsolute = it.javaPath.replace("~", System.getProperty("user.home"))

        if(!File(jarFileAbsolute).exists()){
            log.error("jar file $jarFileAbsolute does not exist")
            return@forEach
        }

        val outputFile = Paths.get(outputDir, "${it.project}#${it.benchmark}#${it.params.replace("/", "_")}.json").toFile()
        val params = if(it.params.isBlank()){
            ""
        }else{
            it.params.split('&').map{ "-p $it"}.joinToString(separator = " ")
        }
        // $ at end of benchmark name is end anchor of regex
        val cmd = "$javaPathAbsolute -jar $jarFileAbsolute ${it.benchmark}$ $params ${it.arguments} $constantArguments -rff $outputFile"

        val start = System.currentTimeMillis()
        executeCommand(cmd)
        val end = System.currentTimeMillis()

        output.appendln("${it.project};${it.benchmark};${it.params};${(end-start)/1000.0}")
        output.flush()

        if(!outputFile.exists()){
            log.error("Result of benchmark ${it.benchmark} with the JMH params ${it.params} is not in output file")
        }
    }
}

fun executeCommand(cmd: String, envVars: Array<String>? = null, dir: File? = null) {
    val process = Runtime.getRuntime().exec(cmd, envVars, dir)

    process.inputStream.reader().use {}
    process.errorStream.reader().use {}

    process.waitFor()
}