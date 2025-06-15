package com.example.seamlesstranslation.data

import android.os.Environment
import java.io.File

/**
 * VoiceDataの保存場所。たぶんもっと良い書き方ある。
 */
object InputVoiceData {
    const val fileName : String = "VoiceData.3gp"
    val filePath : File = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath} + ${File.pathSeparator} + ${fileName}")
}