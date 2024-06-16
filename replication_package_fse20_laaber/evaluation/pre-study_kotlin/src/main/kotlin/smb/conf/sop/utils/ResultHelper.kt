package smb.conf.sop.utils

fun Collection<Result>.nothingSet(): Collection<Result> {
    return filter {
        it.nothingSet()
    }
}

fun Result.nothingSet(): Boolean {
    return warmupIterations == null && warmupTime == null && measurementIterations == null && measurementTime == null &&
            forks == null && warmupForks == null && (modeIsThroughput == null || modeIsThroughput == false)
            && (modeIsAverageTime == null || modeIsAverageTime == false) && (modeIsSampleTime == null || modeIsSampleTime == false)
            && (modeIsSingleShotTime == null || modeIsSingleShotTime == false)
}

fun Result.onlyModeChanged(): Boolean {
    return warmupIterations == null && warmupTime == null && measurementIterations == null && measurementTime == null &&
            forks == null && warmupForks == null
}


fun Collection<Double>.median(): Double {
    return sorted().let {
        (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
    }
}