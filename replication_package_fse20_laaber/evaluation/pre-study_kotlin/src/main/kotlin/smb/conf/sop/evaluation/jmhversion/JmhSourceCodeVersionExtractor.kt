package smb.conf.sop.evaluation.jmhversion

import ch.uzh.ifi.seal.bencher.JMHVersion
import java.io.File

class JmhSourceCodeVersionExtractor(private val dir: File) {
    fun get(): JMHVersion? {
        var version: String? = null
        dir.walk().forEach {
            val resMaven = checkMaven(it)
            if (resMaven != null) {
                version = resMaven
            }
            val resGradle = checkGradle(it)
            if (resGradle != null) {
                version = resGradle
            }
        }

        return convert(version)
    }

    private fun checkMaven(file: File): String? {
        var found: String? = null

        if (file.isFile && file.name == "pom.xml") {
            found = checkSingleLine(file)
            if (found != null) {
                return found
            }

            found = mavenCheckDependencyBlocks(file)
            if (found != null) {
                return found
            }

        }
        return found
    }

    private fun mavenCheckDependencyBlocks(file: File): String? {
        val content = file.readText().replace("\r", "").replace("\n", "")
        val regex = "<dependency>(.)*?<\\/dependency>".toRegex()
        val match = regex.findAll(content)
        match.forEach {
            val res = checkContent(it.value)
            if (res != null) {
                return res
            }
        }
        return null
    }

    private fun checkGradle(file: File): String? {
        var found: String? = null

        if (file.isFile && file.extension == "gradle") {
            found = checkSingleLine(file)
            if (found != null) {
                return found
            }
        }
        return found
    }

    private fun checkSingleLine(file: File): String? {
        var found: String? = null
        file.forEachLine { line ->
            if (found == null) {
                val res = checkContent(line)
                if (res != null) {
                    found = res
                    return@forEachLine
                }
            }
        }
        return found
    }

    private fun checkContent(input: String): String? {
        if (input.contains("jmhVersion")) {
            jmhVersions.forEach { version ->
                if (input.contains(version)) {
                    return version
                }
            }
        } else if (input.contains("jmh") && input.contains("version") && !input.contains("champeau")) {
            jmhVersions.forEach { version ->
                if (input.contains(version)) {
                    return version
                }
            }
        } else if (input.contains("org.openjdk.jmh")) {
            jmhVersions.forEach { version ->
                if (input.contains(version)) {
                    return version
                }
            }
        }

        return null
    }

    private fun convert(input: String?): JMHVersion? {
        return if (input == null) {
            null
        } else {
            val list = input.split(".")
            val major = list[0].toInt()
            val minor = list[1].toInt()
            val patch = list.getOrNull(2)?.toInt() ?: 0
            JMHVersion(major, minor, patch)
        }
    }

    companion object {
        val jmhVersions = listOf("1.21", "1.20", "1.19", "1.18", "1.17.5", "1.17.4", "1.17.3", "1.17.2", "1.17.1", "1.17", "1.16", "1.15", "1.14.1", "1.14", "1.13", "1.12", "1.11.3", "1.11.2", "1.11.1", "1.11", "1.10.5", "1.10.4", "1.10.3", "1.10.2", "1.10.1", "1.9.3", "1.9.2", "1.9.1", "1.9", "1.8", "1.7.1", "1.7", "1.6.3", "1.6.2", "1.6.1", "1.6", "1.5.2", "1.5.1", "1.5", "1.4.2", "1.4.1", "1.4", "1.3.4", "1.3.3", "1.3.2", "1.3.1", "1.3", "1.2", "1.1.1", "1.1", "1.0.1", "1.0", "0.9.8", "0.9.7", "0.9.6", "0.9.5", "0.9.4", "0.9.3", "0.9.2", "0.9.1", "0.9", "0.8", "0.7.3", "0.7.2", "0.7.1", "0.7", "0.6", "0.5.7", "0.5.6", "0.5.5", "0.5.4", "0.5.3", "0.5.2", "0.5", "0.4.2", "0.4.1", "0.4", "0.3.2", "0.3.1", "0.3", "0.2.1", "0.2", "0.1")
    }
}