package com.example.seamlesstranslation.domain

interface VoiceToTextRepository {
    suspend fun convertVoice2Text()

}