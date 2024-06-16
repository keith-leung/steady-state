package smb.conf.sop.evaluation.java

import java.io.File

abstract class JavaVersionExtractor(private val dir: File, private val name: String) {
    fun get(): String? {
        var version: String? = null
        dir.walkTopDown().forEach {
            val resMaven = checkMaven(it)
            if (resMaven != null) {
                version = resMaven
            }
            val resGradle = checkGradle(it)
            if (resGradle != null) {
                version = resGradle
            }
        }

        return version
    }

    private fun checkMaven(file: File): String? {
        var found: String? = null

        if (file.isFile && file.name == "pom.xml") {
            found = checkMavenCompilerPlugin(file)
            if (found != null) {
                return found
            }

            found = checkMavenProperties(file)
            if (found != null) {
                return found
            }
        }

        return found
    }

    private fun checkGradle(file: File): String? {
        var found: String? = null

        if (file.isFile && file.extension == "gradle") {
            found = checkSingleLine(file) { line -> checkContentGradle(line) }
            if (found != null) {
                return found
            }
        }
        return found
    }

    private fun checkSingleLine(file: File, func: (String) -> String?): String? {
        var found: String? = null
        file.forEachLine { line ->
            if (found == null) {
                val res = func(line)
                if (res != null) {
                    found = res
                    return@forEachLine
                }
            }
        }
        return found
    }

    private fun checkContentGradle(input: String): String? {
        if (input.contains("${name}Compatibility")) {
            jdkVersion.forEach { version ->
                if (input.contains(version)) {
                    return version
                }
            }
        }

        return null
    }

    private fun checkMavenCompilerPlugin(file: File): String? {
        val content = file.readText().replace("\r", "").replace("\n", "")
        //val regex = "<plugin>(.)*?<artifactId>maven-compiler-plugin<\\/artifactId>(.)*?<\\/plugin>".toRegex()
        val regex = "<plugin>(.)*?<\\/plugin>".toRegex()
        val match = regex.findAll(content)
        match.forEach { res ->
            if (res.value.contains("maven-compiler-plugin")) {
                val configuration = res.value.substringAfter("<configuration>").substringBefore("</configuration>")
                val ret = checkContentMaven(configuration)

                if (ret != null) {
                    return ret
                }
            }
        }

        return null
    }

    private fun checkMavenProperties(file: File): String? {
        val content = file.readText()
        val regex = "<properties>(.)*?<\\/properties>".toRegex(RegexOption.DOT_MATCHES_ALL)
        val match = regex.findAll(content)
        match.forEach { res ->
            res.value.lines().forEach {
                val ret = checkContentMaven(it)

                if (ret != null) {
                    return ret
                }
            }
        }

        return null
    }

    private fun checkContentMaven(input: String): String? {
        val res1 = checkRegex("<maven.compiler.$name>(.)*?<\\/maven.compiler.$name>".toRegex(), input)
        if (res1 != null) {
            return res1
        }

        val res2 = checkRegex("<(.)*?$name(.)*?>(.)*?<\\/(.)*?$name(.)*?>".toRegex(), input)
        if (res2 != null) {
            return res2
        }

        return null
    }

    private fun checkRegex(regex: Regex, input: String): String? {
        val match = regex.findAll(input)
        match.forEach { res ->
            jdkVersion.forEach { version ->
                if (res.value.contains(version)) {
                    return version
                }
            }
        }

        return null
    }

    companion object {
        val jdkVersion = listOf("12", "11", "10", "9", "1.8", "1.7", "1.6", "1.5", "1.4", "1.3", "1.2", "1.1", "JavaVersion.VERSION_12", "JavaVersion.VERSION_11", "JavaVersion.VERSION_1_10", "JavaVersion.VERSION_1_9", "JavaVersion.VERSION_1_8", "JavaVersion.VERSION_1_7", "JavaVersion.VERSION_1_6", "JavaVersion.VERSION_1_5", "JavaVersion.VERSION_1_4", "JavaVersion.VERSION_1_3", "JavaVersion.VERSION_1_2", "JavaVersion.VERSION_1_1")
    }
}