package com.example.seamlesstranslation.data

import  com.example.seamlesstranslation.domain.RecordRepository
import android.app.Service
import android.content.Intent
import android.os.IBinder

class RecordRepoImpl : RecordRepository, Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override suspend fun storeVoiceData() {

    }

}