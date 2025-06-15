package com.example.seamlesstranslation.service

import android.Manifest
import android.app.ForegroundServiceStartNotAllowedException
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.seamlesstranslation.R
import com.example.seamlesstranslation.data.RecordRepoImpl
import com.example.seamlesstranslation.data.VoiceToTextRepoImpl
import com.example.seamlesstranslation.domain.usecase.RecordUseCase
import com.example.seamlesstranslation.domain.usecase.VoiceToTextUseCase
import com.example.seamlesstranslation.service.receiver.RecordServiceReceiver

private const val LOG_TAG = "RecordService"

/**
 * ForegroundServiceを起動するクラス
 * ウィジェットのボタンから呼び出され、RecordUseCase→RecordRepoImplに処理を移譲
 */
class RecordService : Service() {

    private lateinit var recordUseCase: RecordUseCase
    private lateinit var voiceToTextUseCase :VoiceToTextUseCase
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = this.applicationContext
        // 依存注入
        val recordImpl = RecordRepoImpl(context)
        recordUseCase = RecordUseCase(recordImpl)
        val voiceToTextImpl = VoiceToTextRepoImpl(context)
        voiceToTextUseCase = VoiceToTextUseCase(voiceToTextImpl)
    }

    /**
     * BroadCastReceiverで受け取ったIntentで処理
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null && intent.action != null) {
            when (intent.action) {
                //GlanceWidgetから、PendingIntentを分けて飛ばせれば実装できる
                "START_RECORDING" -> {
                    startForeground()
                    Log.d("foreground", "Foreground is ok")
                    recordUseCase.startRecording()
                    Log.d("start", "startRecording is ok")
                }
                "STOP_RECORDING" -> {
                    recordUseCase.stopRecording()
                    Log.d("stop", "stopRecording is ok")
                    stopSelf()
                }
            }
        }

        return START_STICKY
    }

    private fun startForeground() {
        // Before starting the service as foreground check that the app has the
        val recordPermission =
            PermissionChecker.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (recordPermission != PermissionChecker.PERMISSION_GRANTED) {
            stopSelf()
            return
        }
        Log.d("permission", "ok")

        try {
            val stopIntent = Intent(this, RecordServiceReceiver::class.java).apply {
                action = "STOP_RECORDING"
                putExtra(Notification.EXTRA_NOTIFICATION_ID, 0)
            }
            val stopPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0,
                    stopIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            val notification = NotificationCompat.Builder(this, "record_service_channel")
                // Create the notification to display while the service is running
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("audioRecord")
                .setContentText("録音中")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_home_black_24dp, "stop", stopPendingIntent)
                .build()
            ServiceCompat.startForeground(
                /* service = */ this,
                /* id = */ 100, // Cannot be 0
                /* notification = */ notification,
                /* foregroundServiceType = */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE
                } else {
                    0
                },
            )
        } catch (e: Exception) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                && e is ForegroundServiceStartNotAllowedException
            ) {
                // App not in a valid state to start foreground service
                // (e.g. started from bg)
            }
            // ...
        }
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
    }
}