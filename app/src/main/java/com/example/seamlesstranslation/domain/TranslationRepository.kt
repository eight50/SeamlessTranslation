package com.example.seamlesstranslation.domain

interface TranslationRepository {
    suspend fun translate()

    suspend fun storeData()
}