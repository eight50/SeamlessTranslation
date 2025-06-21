package com.example.seamlesstranslation.data

import android.content.Context
import java.io.File

data class VoiceToTextData(private val context : Context) {
    private val fileName = "voiceToTextData.txt"
    private val filePath : File = File(context.filesDir, fileName)

    fun getFilePath() : File {
        return filePath
    }
}
