package com.example.seamlesstranslation.domain.usecase

import com.example.seamlesstranslation.domain.repository.TranslationRepository

/**
 * 翻訳するための外部通信のユースケース
 */
class TranslationUseCase(private val repository: TranslationRepository) {
    fun translate() = repository.translate()
    fun storeData() = repository.storeData()
}