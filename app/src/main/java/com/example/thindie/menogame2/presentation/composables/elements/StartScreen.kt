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
    name: String?,
    onNewGame: () -> Unit,
    onMaster: () -> Unit,
    onSavePlayer: (String) -> Unit,
    onRecord: () -> Unit,
    onExit: () -> Unit
) {
    var textFieldState by rememberSaveable { mutableStateOf("") }
    var labelState by rememberSaveable { mutableStateOf(name ?: "") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        contentColor = MaterialTheme.colorScheme.onSurface,
        color = MaterialTheme.colorScheme.surface,
        elevation = 150.dp
    ) {
        Column(
            modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = textFieldState,
                onValueChange = { textFieldState = it },
                singleLine = true,
                label = { Text("enter name..") },
                shape = Shapes.extraLarge,
                modifier = modifier.padding(top = 40.dp),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.onSurface,
                    focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                    trailingIconColor = MaterialTheme.colorScheme.outline,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        labelState = textFieldState;
                        textFieldState = "";
                        keyboardController?.hide()
                        onSavePlayer(labelState)
                    }) {
                        Icon(imageVector = Icons.Default.SaveAlt, contentDescription = "")
                    }
                })
            if (textFieldState != "" || labelState != "") {
                if (labelState != "") {
                    onSavePlayer(labelState)
                    Text(
                        text = labelState,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = modifier.padding(top = 20.dp)
                    )
                }
                Spacer(modifier = modifier.weight(0.3f))
                Animate()
                Spacer(modifier = modifier.weight(0.3f))
                ButtonStartScreen({ onMaster() }, description = "Im master")
                ButtonStartScreen({ onNewGame() }, "New Game")
                ButtonStartScreen({ onRecord()}, "Records")
                ButtonStartScreen({ onExit() }, "Exit")
            }


        }
    }
}


@Composable
fun ButtonStartScreen(onClick: () -> Unit, description: String) {
    Button(
        onClick = { onClick() },
        shape = Shapes.extraLarge,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .width(260.dp)
            .height(50.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onSurface
        )

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
