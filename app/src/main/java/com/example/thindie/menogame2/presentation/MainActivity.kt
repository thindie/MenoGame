package com.example.thindie.menogame2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.thindie.menogame2.presentation.composables.elements.State
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme
import dagger.hilt.android.AndroidEntryPoint

private const val INITIAL_LOADING = 1000L

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onLoadScreen(INITIAL_LOADING)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            MenoGame2Theme {
                State { this@MainActivity.onDestroy() }
            }
        }

    }
}