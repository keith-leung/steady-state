package smb.conf.sop.utils

import org.apache.commons.codec.binary.Hex

val String.toGithubName: String
    inline get() = this.replace("#", "/")

val String.toFileSystemName: String
    inline get() = this.replace("/", "#")

val ByteArray.convertToString: String
    inline get() = Hex.encodeHexString(this)

val String.outerClass: String
    inline get() = this.substringBefore('#')

fun Boolean?.toInt(): Int? {
    return this?.toInt()
}

fun Boolean.toInt(): Int {
    return if (this) {
        1
    } else {
        0
    }
}

fun Long.toSecond(): Double {
    return this / 1000000000.0
}