package com.example.seamlesstranslation.widget.ui


import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.ButtonColors
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.color.ColorProvider
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionSendBroadcast
import androidx.glance.appwidget.action.actionStartService
import androidx.glance.appwidget.provideContent
import androidx.glance.color.ColorProviders
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.example.seamlesstranslation.service.RecordService
import com.example.seamlesstranslation.service.receiver.RecordServiceReceiver

/**
 * ウィジェットのレンダリングに必要なデータを読み込む
 */
class AppWidget : GlanceAppWidget() {


    companion object {
        private val SMALL_SQUARE = DpSize(100.dp, 100.dp)
        private val HORIZONTAL_RECTANGLE = DpSize(250.dp, 100.dp)
    }

    /**
     * レスポンシブレイアウト
     */
    override val sizeMode = SizeMode.Responsive(
        setOf(
            SMALL_SQUARE,
            HORIZONTAL_RECTANGLE
        )
    )

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
    private fun AppWidgetContent(
    ) {
        Row(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // BroadCastを利用してWidgetからRecordServiceを起動する
            val context = LocalContext.current
            val startIntent = Intent(context, RecordServiceReceiver::class.java).apply {
                action = "START_RECORDING"
            }
            val stopIntent = Intent(context, RecordServiceReceiver::class.java).apply {
                action = "STOP_RECORDING"
            }
            Button(
                text = "Start\nRec",
                onClick = actionSendBroadcast(intent = startIntent),
//                //ButtonColorsがinternalClassなので、まだ色の変更できないらしい
//                colors = ButtonColors(
//                    backgroundColor = ColorProvider(day = Color.Green, night = Color.Green),
//                    contentColor = ColorProvider(day = Color.Black, night = Color.Black)
//                )
            )
            Button(
                text = "Stop\nRec",
                onClick = actionSendBroadcast(intent = stopIntent),
//                colors = ButtonColors(
//                    containerColor = Color.Red,
//                    contentColor = Color.Black,
//                    disabledContainerColor = Color.Gray,
//                    disabledContentColor = Color.White
//                )
            )
        }
    }
}

