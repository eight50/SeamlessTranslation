package com.example.seamlesstranslation.widget.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.Button
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.action.actionStartService
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import com.example.seamlesstranslation.service.RecordService

/**
 * ウィジェットのレンダリングに必要なデータを読み込む
 */
class AppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            AppWidgetContent()
        }
    }

    /**
     * RecordServiceを呼び出すボタンを配置
     */
    @Composable
    private fun AppWidgetContent() {
        Column(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                text = "recordVoice",
                onClick = actionStartService<RecordService>(
                    isForegroundService = true
                )
            )
        }
    }
}