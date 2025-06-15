package com.example.seamlesstranslation.data

import android.content.Context
import android.os.Environment
import java.io.File

/**
 * VoiceDataの保存場所。たぶんもっと良い書き方ある。
 */
class InputVoiceData(private val context : Context) {
    private val fileName : String = "VoiceData.wave"
    // context.fileDirで内部ストレージ保存がいいかも
    // val filePath : File = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath} + ${File.pathSeparator} + ${fileName}")
    private val filePath : File = File(context.filesDir, fileName)

    fun getFilePath() : File {
        return filePath
    }
}