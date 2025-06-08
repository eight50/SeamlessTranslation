package com.example.seamlesstranslation.data

import android.os.Environment
import java.io.File

object InputVoiceData {
    const val fileName : String = "VoiceData.3gp"
    val filePath : File = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath}/${fileName}")
}