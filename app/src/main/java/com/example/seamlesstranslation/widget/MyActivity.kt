package com.example.seamlesstranslation.widget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * サンプルコード、今は使ってない
 */
class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Text(text = stringResource(id = R.string.app_name))
        }
    }
}