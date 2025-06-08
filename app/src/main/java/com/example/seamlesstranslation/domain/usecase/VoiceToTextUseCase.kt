package com.example.seamlesstranslation.domain.usecase

import com.example.seamlesstranslation.domain.repository.VoiceToTextRepository

/**
 * 音声ファイルをテキストファイルへと変換するユースケース
 */
class VoiceToTextUseCase(private val repository: VoiceToTextRepository) {
    fun convertVoiceToText() = repository.convertVoiceToText()
}