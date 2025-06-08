package com.example.seamlesstranslation.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.seamlesstranslation.service.RecordService


class RecordServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        val serviceIntent = Intent(context, RecordService::class.java)

        if ("STOP_RECORDING" == action) {
            serviceIntent.setAction("STOP_RECORDING")
        }

        context.startService(serviceIntent)
    }
}