package com.example.seamlesstranslation.data

import android.content.Context
import android.util.Log

import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.SpeechStreamService
import org.vosk.android.StorageService
import java.io.FileInputStream
import java.io.FileWriter
import java.io.IOException

import com.example.seamlesstranslation.domain.repository.VoiceToTextRepository
import com.example.seamlesstranslation.data.InputVoiceData
import java.io.File


// 引数内でvalを付けるとフィールド化（他のメソッドに見える）
class VoiceToTextRepoImpl(
    private val context: Context
) : VoiceToTextRepository {
    private var model : Model? = null
    private var recognizer : Recognizer? = null
    private var speechStreamService : SpeechStreamService? = null
    private val inputVoicePath : File = InputVoiceData(context).getFilePath()
    private val voiceToTextPath : File = VoiceToTextData(context).getFilePath()

    override fun convertVoiceToText() {
        val sourcePath : String = "language_model/vosk-model-small-en-us-0.15"
        val targetPath : String = "model"
        val samplingRate = 16000.0f

        initModel(sourcePath, targetPath, samplingRate)

        // .use : Closeableなリソースに有効、使い終わったらclose()
        FileInputStream(inputVoicePath).use { ais ->
            val buffer = ByteArray(4096)
            var bytesRead: Int
            while ((ais.read(buffer).also { bytesRead = it }) >= 0) {
                if (recognizer!!.acceptWaveForm(buffer, bytesRead)) {
                    println(recognizer!!.result)
                }
            }
            val finalResult = recognizer!!.finalResult
            FileWriter(voiceToTextPath).use { writer ->
                writer.write(finalResult)
            }
        }
    }


    // assetsフォルダ内にあるモデルを内部ストレージにコピーしている
    private fun initModel(sourcePath : String, targetPath : String, samplingRate : Float) {
        StorageService.unpack(context, sourcePath, targetPath,
            // callBackでコピーしたモデルが返ってくる
            { model: Model? ->
                this.model = model
                if(this.recognizer != null) {
                    this.recognizer?.close()
                }
                if(model != null) {
                    this.recognizer = Recognizer(model, samplingRate)
                } else {
                    Log.e("initError", "model not found")
                }
            },
            { exception: IOException -> exception.printStackTrace() })
    }

//    private fun initRecognizer(model: Model?, samplingRate : Float) {
//        if(recognizer != null) {
//            recognizer?.close()
//        }
//        if(model != null) {
//            recognizer = Recognizer(model, samplingRate)
//        } else {
//            Log.e("initError", "model not found")
//        }
//    }
}