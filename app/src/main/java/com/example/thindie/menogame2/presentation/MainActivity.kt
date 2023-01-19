package com.example.thindie.menogame2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thindie.menogame2.domain.entities.GameResult
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {

                    setContent {
                        MenoGame2Theme {
                            when (it) {
                                is MainViewModel.ViewState.StartScreen -> {
                                    Surface {
                                        Text(
                                            "click",
                                            modifier = Modifier.clickable { viewModel.onStartGame() })
                                    }
                                }
                                is MainViewModel.ViewState.PlayScreen<*> -> {
                                    Surface {
                                        Text("Play", modifier = Modifier.clickable {
                                            viewModel.onUserActInGame(
                                                GameResult<Long>(
                                                    mutableListOf(),
                                                    mutableListOf(0),
                                                    mutableListOf()
                                                )
                                            )
                                        })
                                    }
                                }
                                is MainViewModel.ViewState.EndScreen<*> -> {
                                    Surface {
                                        Text("End", modifier = Modifier.clickable {
                                            viewModel.onEndGame()
                                        }

                                        )
                                    }
                                }

                                else -> {}

                            }

                        }
                    }
                }
            }
        }
    }
}