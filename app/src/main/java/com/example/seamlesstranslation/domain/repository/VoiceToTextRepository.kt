package com.example.seamlesstranslation.domain.repository

interface VoiceToTextRepository {
    suspend fun convertVoiceToText()
}