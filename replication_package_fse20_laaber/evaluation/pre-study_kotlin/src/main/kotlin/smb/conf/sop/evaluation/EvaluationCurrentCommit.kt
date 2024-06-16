package smb.conf.sop.evaluation

import smb.conf.sop.evaluation.history.HistoryManager
import smb.conf.sop.utils.toFileSystemName
import org.eclipse.jgit.api.Git
import java.io.File
import java.nio.file.Paths

class EvaluationCurrentCommit(inputFile: File, inputDir: String, outputDir: File, outputFile: File) : Evaluation(inputFile, inputDir, outputDir, outputFile) {
    override fun processProject(project: String, sourceDir: File, outputDir: File, outputFile: File) {
        super.processProject(project, sourceDir, outputDir, outputFile)
        val repository = HistoryManager.getRepo(sourceDir)
        val git = Git(repository)
        val commits = HistoryManager.sampleCommits(repository, git)
        val currentCommit = commits.first()
        val commitId = currentCommit.second.name
        val commitTime = currentCommit.first
        evaluate(project, commitId, commitTime, sourceDir, Paths.get(outputDir.absolutePath, "${project.toFileSystemName}.csv"), outputFile)
    }
}