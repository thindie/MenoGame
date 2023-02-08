package com.example.thindie.menogame2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.thindie.menogame2.presentation.composables.elements.State
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)
        viewModel.onStart()
        setContent {
            MenoGame2Theme {
                State()
            }
        }

    }
}