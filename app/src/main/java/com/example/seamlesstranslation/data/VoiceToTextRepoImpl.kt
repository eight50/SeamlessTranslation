package com.example.seamlesstranslation.data

import android.app.Application
import android.content.Context
import com.example.seamlesstranslation.domain.repository.VoiceToTextRepository

import org.vosk.Model
import org.vosk.BuildConfig
import org.vosk.android.SpeechService
import org.vosk.android.SpeechStreamService
import java.io.File

// 引数内でvalを付けるとフィールド化（他のメソッドに見える）
class VoiceToTextRepoImpl(
    private val context: Context
) : VoiceToTextRepository {
    private var model : Model? = null
    private var speechService: SpeechService? = null
    private var speechStreamSercice: SpeechStreamService? = null


    override fun convertVoiceToText() {

    }

    private fun initModel() {
    }
}