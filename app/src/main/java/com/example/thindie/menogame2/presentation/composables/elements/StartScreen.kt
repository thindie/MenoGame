package com.example.thindie.menogame2.presentation.composables.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.theme.Shapes


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onNewGame: () -> Unit,
    onRecord: () -> Unit,
    onExit: () -> Unit
) {
    var textFieldState by rememberSaveable { mutableStateOf("") }
    var labelState by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = textFieldState,
                onValueChange = { textFieldState = it },
                singleLine = true,
                label = { Text("enter name..") },
                shape = Shapes.extraLarge,
                modifier = modifier.padding(top = 40.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        labelState = textFieldState; textFieldState =
                        ""; keyboardController?.hide()
                    }) {
                        Icon(imageVector = Icons.Default.SaveAlt, contentDescription = "")
                    }
                })
            if (textFieldState != "" || labelState != "") {
                if (labelState != "") {
                    Text(
                        text = labelState,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = modifier.padding(top = 20.dp)
                    )
                }
                Spacer(modifier = modifier.weight(0.3f))
                Animate()
                Spacer(modifier = modifier.weight(0.3f))
                ButtonStartScreen({onNewGame()}, "New Game")
                ButtonStartScreen({}, "Records")
                ButtonStartScreen({}, "Exit")
            }


        }
    }
}


@Composable
fun ButtonStartScreen(onClick: () -> Unit, description: String) {
    OutlinedButton(
        onClick = { onClick() },
        shape = Shapes.extraLarge,
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .width(260.dp)

    ) {
        Text(description, style = MaterialTheme.typography.headlineSmall)
    }
}


@Composable
fun Animate() {
    val list = mutableListOf<Color>()


    with(MaterialTheme.colorScheme) {
        list.add(onSurface)
        list.add(onSurfaceVariant)
        list.add(inverseOnSurface)
        list.add(onPrimary)
        list.add(onError)
    }
    LazyRow() {
        items(list) {
            Element(color = it)
        }
    }
}


@Composable
fun Element(color: Color) {
    FloatingActionButton(
        shape = CircleShape, onClick = { }, containerColor = color
    ) {

    }
}