package com.example.seamlesstranslation.data

import com.example.seamlesstranslation.domain.repository.RecordRepository
import com.example.seamlesstranslation.data.InputVoiceData
import android.media.MediaRecorder
import android.util.Log
import java.io.File
import java.io.IOException

private const val LOG_TAG = "RecordRepoImpl"

/**
 * RecordRepositoryの具象クラス
 * MediaRecorderを使って録音する
 */
class RecordRepoImpl (): RecordRepository{
    private var recorder : MediaRecorder? = null
    private var isRecording : Boolean = false
    private val filePath : File = InputVoiceData.filePath

    override fun startRecording() {
        if(isRecording) return

        Log.d("LOG_TAG", filePath.toString())
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFile(filePath)
            // .3gp 軽量だが音質悪い(AMR_NBEncoderで8kHzまで)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e : IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            try {
                start()
            } catch (e : Exception) {
                Log.e(LOG_TAG, "start() failed")
            }
            isRecording = true
            Log.e(LOG_TAG, "recording start")
        }
    }

    override fun stopRecording() {
        if (!isRecording) return
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    /**
     * MediaRecorderで保存するから必要ないかも
     */
    override fun storeVoiceData() {
    }
}