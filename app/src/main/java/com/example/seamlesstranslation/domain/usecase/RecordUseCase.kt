package com.example.seamlesstranslation.domain.usecase

import com.example.seamlesstranslation.domain.repository.RecordRepository

class RecordUseCase(private val repository : RecordRepository) {
    fun startRecording() = repository.startRecording()
    fun stopRecording() {
        repository.stopRecording()
        repository.storeVoiceData()
    }
}