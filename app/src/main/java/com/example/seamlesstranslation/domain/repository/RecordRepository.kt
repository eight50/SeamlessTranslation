package com.example.seamlesstranslation.domain.repository

interface RecordRepository {
    fun startRecording()
    fun stopRecording()
    fun storeVoiceData()
}