package smb.conf.dynreconf.utils

import kotlin.math.pow
import kotlin.math.sqrt

fun List<Double>.std(): Double {
    val mean = average()
    val sd = fold(0.0, { accumulator, next -> accumulator + (next - mean).pow(2.0) })
    return sqrt(sd / size)
}