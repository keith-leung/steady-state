package smb.conf.dynreconf.utils

data class CsvLineKey(
        val project: String,
        val commit: String,
        val benchmark: String,
        val params: String
) {

    fun output(): String {
        return "$project;$commit;$benchmark;$params"
    }
}