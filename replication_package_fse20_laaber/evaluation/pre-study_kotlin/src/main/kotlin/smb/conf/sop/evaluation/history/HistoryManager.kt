package smb.conf.sop.evaluation.history

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.ResetCommand
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object HistoryManager {
    fun sampleCommits(repository: Repository, git: Git): List<Pair<Int, RevCommit>> {
        val ret = mutableListOf<Pair<Int, RevCommit>>()
        val commits = git.log().add(repository.resolve(repository.fullBranch)).call().toList()
        commits.sortedByDescending {
            it.commitTime
        }

        val firstCommit = commits.first()
        ret.add(Pair(firstCommit.commitTime, firstCommit))

        var timepoint = getFirstOfMonthAsTimestamp(commits.first().commitTime)
        while (timepoint > JMH_STARTS) {
            val nextCommit = commits.filter { it.commitTime >= timepoint }.last()
            val prevCommit = commits.filter { it.commitTime < timepoint }.firstOrNull() ?: break

            val nextCommitDelta = nextCommit.commitTime - timepoint
            val prevCommitDelta = timepoint - prevCommit.commitTime

            if (nextCommitNearEnough(nextCommitDelta, timepoint) && nextCommitDelta < prevCommitDelta) {
                ret.add(Pair(timepoint, nextCommit))
            } else if (prevCommitNearEnough(prevCommitDelta, timepoint)) {
                ret.add(Pair(timepoint, prevCommit))
            }

            timepoint = getPrevMonth(timepoint)
        }

        val lastCommit = commits.last()
        if (!ret.map { it.second }.contains(lastCommit)) {
            ret.add(Pair(lastCommit.commitTime, lastCommit))
        }

        return ret
    }

    fun getRepo(dir: File): Repository {
        val repoDir = File("${dir.absolutePath}/.git")

        // remove lock
        repoDir.walkTopDown().forEach {
            if (it.isFile && it.name.contains("index.lock")) {
                it.delete()
            }
        }

        val builder = FileRepositoryBuilder()
        return builder.setGitDir(repoDir)
                .readEnvironment()
                .findGitDir()
                .build()
    }

    fun resetToBranch(git: Git) {
        val branchName = git.branchList().call().filter { it.name != "HEAD" }.first().name.substringAfter("refs/heads/")
        git.reset().setMode(ResetCommand.ResetType.HARD).call()
        git.checkout().setName(branchName).setForced(true).call()
    }

    fun resetToHead(git: Git, commitId: String) {
        git.reset().setMode(ResetCommand.ResetType.HARD).call()
        git.checkout().setName(commitId).setForced(true).call()
    }

    private fun getFirstOfMonthAsTimestamp(timestamp: Int): Int {
        val date = Date(timestamp * 1000L)
        val year = SimpleDateFormat("yyyy").format(date).toInt()
        val month = SimpleDateFormat("MM").format(date).toInt()
        return getFirstOfMonthAsTimestamp(year, month)
    }

    private fun getFirstOfMonthAsTimestamp(year: Int, month: Int): Int {
        val cal = GregorianCalendar(year, month - 1, 1)
        return (cal.timeInMillis / 1000).toInt()
    }

    private fun getNextMonth(timestamp: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timestamp * 1000L)
        calendar.add(Calendar.MONTH, 1)
        val prevMonth = calendar.time
        return (prevMonth.time / 1000).toInt()
    }

    private fun getPrevMonth(timestamp: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timestamp * 1000L)
        calendar.add(Calendar.MONTH, -1)
        val prevMonth = calendar.time
        return (prevMonth.time / 1000).toInt()
    }

    private fun nextCommitNearEnough(delta: Int, timestampCurrentMonth: Int): Boolean {
        val timestampNextMonth = getNextMonth(timestampCurrentMonth)
        val maxDelta = (timestampNextMonth - timestampCurrentMonth) / 2
        return delta <= maxDelta
    }

    private fun prevCommitNearEnough(delta: Int, timestampCurrentMonth: Int): Boolean {
        val timestampPrevMonth = getPrevMonth(timestampCurrentMonth)
        val maxDelta = (timestampCurrentMonth - timestampPrevMonth) / 2
        return delta <= maxDelta
    }

    private const val JMH_STARTS = 1401580799 // version 0.9 released
}