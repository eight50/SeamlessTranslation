package com.example.seamlesstranslation.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text

/**
 * サンプルコード、あまり使い方わからない
 */
class ManageAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
            Text("Hello World")
        }
    }

//    @Composable
//    fun MyContent() {
//        val repository = remember { DestinationsRepository.getInstance() }
//        // Retrieve the cache data everytime the content is refreshed
//        val destinations by repository.destinations.collectAsState(State.Loading)
//
//        when (destinations) {
//            is State.Loading -> {
//                // show loading content
//            }
//
//            is State.Error -> {
//                // show widget error content
//            }
//
//            is State.Completed -> {
//                // show the list of destinations
//            }
//        }
//    }

    suspend fun updateAppWidget(context: Context, glanceId: GlanceId) {
        // [START android_compose_glance_manageupdate03]
        val manager = GlanceAppWidgetManager(context)
        val widget = GlanceSizeModeWidget()
        val glanceIds = manager.getGlanceIds(widget.javaClass)
        glanceIds.forEach { glanceId ->
            widget.update(context, glanceId)
        }
    }
}

/** dummy class */
class GlanceSizeModeWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        TODO("Not yet implemented")
    }
}