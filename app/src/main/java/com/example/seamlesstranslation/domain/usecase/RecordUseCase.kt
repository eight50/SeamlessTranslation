package com.example.seamlesstranslation.domain.usecase

import com.example.seamlesstranslation.domain.repository.RecordRepository

class RecordUseCase(private val repository : RecordRepository) {
    suspend fun startRecording() = repository.startRecording()
    suspend fun stopRecording() {
        repository.stopRecording()
        repository.storeVoiceData()
    }
}