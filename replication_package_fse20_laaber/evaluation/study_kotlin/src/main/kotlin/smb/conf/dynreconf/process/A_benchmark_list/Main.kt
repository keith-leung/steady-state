package smb.conf.dynreconf.process.A_benchmark_list

import ch.uzh.ifi.seal.bencher.analysis.finder.asm.AsmBenchFinder
import smb.conf.dynreconf.EvaluationPath
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 6) {
        println("Needed arguments: project jarFile packagePrefix javaPath arguments outFile")
        exitProcess(-1)
    }

    val project = args[0]
    val jarPath = EvaluationPath.get("study_subjects", args[1])
    val packageName = args[2]
    val javaPath = args[3]
    val arguments = args[4]
    val outFile = args[5]

    val jar = File(jarPath)
    val bf = AsmBenchFinder(jar, packageName).all()

    if (bf.isLeft()) {
        throw RuntimeException(bf.left().get())
    }

    File(EvaluationPath.get("study_subjects", outFile))
        .printWriter()
        .use { out ->
            out.println("project;benchmark;params;javaPath;jarFile;arguments")
            bf.right().get().forEach {
                it.parameterizedBenchmarks().forEach {
                    val params = it.jmhParams.map { "${it.first}=${it.second}" }.joinToString("&")
                    val benchName = if (it.group == null) {
                        "${it.clazz}.${it.name}"
                    } else {
                        "${it.clazz}.${it.group}"
                    }
                    out.println("$project;$benchName;$params;$javaPath;${jarPath.toString()};$arguments")
                }
            }
        }
}
