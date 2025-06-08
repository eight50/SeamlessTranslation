package com.example.seamlesstranslation.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.seamlesstranslation.service.RecordService

/**
 * RecordServiceのためのBroadCastを受け取る
 */
class RecordServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        val serviceIntent = Intent(context, RecordService::class.java)
        val startAction : String = "START_RECORDING"
        val stopAction : String = "STOP_RECORDING"

        if (startAction == action) {
            serviceIntent.setAction(startAction)
        } else if (stopAction == action) {
            serviceIntent.setAction(stopAction)
        }

        context.startService(serviceIntent)
    }
}