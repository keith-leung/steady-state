package smb.conf.sop.evaluation.java

import java.io.File

class JavaTargetVersionExtractor(private val dir: File) : JavaVersionExtractor(dir, "target")