package com.example.thindie.menogame2.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WinnerCard() {

    val field = remember { mutableStateOf("") }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Surface(
            tonalElevation = 80.dp, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .padding(all = 20.dp),
            shape = ShapeDefaults.ExtraLarge,
            shadowElevation = 110.dp
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp)
            ) {
                Text(text = "1", style = MaterialTheme.typography.headlineLarge)
                Divider(Modifier.padding(all = 10.dp))
                Text(text = "text", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.weight(0.2f))
                TextField(
                    value = field.value,
                    onValueChange = { field.value = it },
                    trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = "save")
                        }
                    },
                    shape = ShapeDefaults.ExtraLarge,
                    
                )
                Spacer(modifier = Modifier.weight(0.2f))
            }
        }
    }

}

@Preview
@Composable
fun previewWinnerCard() {
    MenoGame2Theme {
        WinnerCard()
    }
}