package smb.conf.sop.utils

import com.opencsv.bean.CsvBindByPosition

class Row {
    @CsvBindByPosition(position = 0)
    var project: String = ""

    @CsvBindByPosition(position = 1)
    var forked: Boolean = false

    @CsvBindByPosition(position = 2)
    var stars: Int? = null

    @CsvBindByPosition(position = 3)
    var forks: Int? = null

    @CsvBindByPosition(position = 4)
    var watchers: Int? = null

    // we get a 404 error from the github api -> repo not available
    @CsvBindByPosition(position = 5)
    var repoAvailable: Boolean = true

    // full name of the parent project (null if not a forked project)
    @CsvBindByPosition(position = 6)
    var parentProject: String? = null

    // full name of the root project (null if not a forked project)
    @CsvBindByPosition(position = 7)
    var rootProject: String? = null

    @CsvBindByPosition(position = 8)
    var mainRepo: Boolean? = null

    @CsvBindByPosition(position = 9)
    var cloneUrl: String? = null

    @CsvBindByPosition(position = 10)
    var lastUpdate: String? = null

    @CsvBindByPosition(position = 11)
    var numberOfCommits: Int? = null

    @CsvBindByPosition(position = 12)
    var numberOfTags: Int? = null

    @CsvBindByPosition(position = 13)
    var numberOfContributors: Int? = null

    @CsvBindByPosition(position = 14)
    var jmhVersion: String? = null

    @CsvBindByPosition(position = 15)
    var numberOfBenchmarks: Int? = null

    @CsvBindByPosition(position = 16)
    var javaTarget: String? = null

    @CsvBindByPosition(position = 17)
    var javaSource: String? = null

    @CsvBindByPosition(position = 18)
    var firstBenchmarkFound: Int? = null

    @CsvBindByPosition(position = 19)
    var lastCommit: Int? = null

    @CsvBindByPosition(position = 20)
    var lastCommitHash: String? = null

    @CsvBindByPosition(position = 21)
    var defaultBranch: String? = null

    constructor()
}