package com.example.seamlesstranslation.data

import com.example.seamlesstranslation.domain.repository.RecordRepository
import com.example.seamlesstranslation.data.InputVoiceData
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder
import android.util.Log
import java.io.IOException

private const val LOG_TAG = "AudioRecord"

class RecordRepoImpl : RecordRepository, Service() {
    private var recorder : MediaRecorder? = null

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    /**
     * MediaRecorder
     */
    override suspend fun startRecording() {
        val context : Context = this
        val fileName : String = InputVoiceData.fileName

        recorder = MediaRecorder(context).apply {
            setOutputFile(fileName)
            setAudioSource(MediaRecorder.AudioSource.MIC)
            // .3gp 軽量だが音質悪い(AMR_NBEncoderで8kHzまで)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e : IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    override suspend fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    override suspend fun storeVoiceData() {

    }

}