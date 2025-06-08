package com.example.seamlesstranslation.domain.usecase

import com.example.seamlesstranslation.domain.repository.RecordRepository

/**
 * 録音するユースケース。インスタンス化の際にはRepoImplで依存注入。
 */
class RecordUseCase(private val repository : RecordRepository) {
    fun startRecording() = repository.startRecording()
    fun stopRecording() {
        repository.stopRecording()
        repository.storeVoiceData()
    }
}