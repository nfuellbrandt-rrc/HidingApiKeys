package com.example.hidingapikeys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.hidingapikeys.ui.theme.HidingApiKeysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HidingApiKeysTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val apiKey = BuildConfig.API_KEY
                    val apiKeyFromGradleProperties = BuildConfig.API_KEY_FROM_GRADLE_PROPERTIES
                    Column (
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(text = apiKey)
                        Text(text = apiKeyFromGradleProperties)
                    }
                }
            }
        }
    }
}