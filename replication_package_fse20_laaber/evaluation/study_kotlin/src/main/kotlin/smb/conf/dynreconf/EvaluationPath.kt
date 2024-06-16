package smb.conf.dynreconf

import java.nio.file.Paths

object EvaluationPath {
    private const val p = "change path to evaluation folder"

    fun get(vararg rest: String): String = Paths.get(p, *rest).toString()
}