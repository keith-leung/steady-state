package smb.conf.sop.evaluation.java

import java.io.File

class JavaSourceVersionExtractor(private val dir: File) : JavaVersionExtractor(dir, "source")