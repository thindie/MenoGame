package com.example.thindie.menogame2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenoGame2Theme {
                // A surface container using the 'background' color from the theme
                Surface(

                ) {

                }
            }
        }
    }
}
