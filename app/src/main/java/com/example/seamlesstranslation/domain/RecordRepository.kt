package com.example.seamlesstranslation.domain

interface RecordRepository {
    suspend fun storeVoiceData()

}