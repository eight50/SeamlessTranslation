package com.example.seamlesstranslation.domain.repository

import android.content.Context

interface RecordRepository {
    fun startRecording()
    fun stopRecording()
    fun storeVoiceData()
}