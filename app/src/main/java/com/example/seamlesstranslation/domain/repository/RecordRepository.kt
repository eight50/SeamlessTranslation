package com.example.seamlesstranslation.domain.repository

import android.content.Context

interface RecordRepository {
    suspend fun startRecording()
    suspend fun stopRecording()
    suspend fun storeVoiceData()
}