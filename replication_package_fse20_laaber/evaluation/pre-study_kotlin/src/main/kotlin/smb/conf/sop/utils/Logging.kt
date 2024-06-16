package smb.conf.sop.utils

import java.io.OutputStream
import java.io.PrintStream

fun disableSystemErr() {
    System.setErr(PrintStream(object : OutputStream() {
        override fun write(b: Int) {}
    }))
}