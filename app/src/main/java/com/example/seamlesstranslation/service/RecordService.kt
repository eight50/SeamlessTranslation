package com.example.seamlesstranslation.service

import android.content.pm.ServiceInfo
import android.Manifest
import android.os.Build
import android.app.Service
import android.app.ForegroundServiceStartNotAllowedException
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.content.PermissionChecker
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.example.seamlesstranslation.data.RecordRepoImpl
import com.example.seamlesstranslation.domain.usecase.RecordUseCase

/**
 * ForegroundServiceを起動するクラス
 * ウィジェットのボタンから呼び出され、RecordUseCase→RecordRepoImplに処理を移譲
 */
class RecordService : Service() {

    private lateinit var recordUseCase : RecordUseCase
    val context : Context = this

    override fun onCreate() {
        super.onCreate()

        // 依存注入
        val repoImpl = RecordRepoImpl()
        recordUseCase = RecordUseCase(repoImpl)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    //
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun startForeground() {
        // Before starting the service as foreground check that the app has the
        // appropriate runtime permissions. In this case, verify that the user has
        // granted the CAMERA permission.
        val recordPermission =
            PermissionChecker.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (recordPermission != PermissionChecker.PERMISSION_GRANTED) {
            // Without camera permissions the service cannot run in the foreground
            // Consider informing user or updating your app UI if visible.
            stopSelf()
            return
        }

        try {
            val notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                // Create the notification to display while the service is running
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

    private suspend fun startRecording() {
        startForeground()
        recordUseCase.startRecording()
    }

    private fun stopRecording() {
        stopSelf()
    }

}
