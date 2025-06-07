package com.example.seamlesstranslation.domain.repository

interface TranslationRepository {
    suspend fun translate()
    suspend fun storeData()
}