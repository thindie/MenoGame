package com.example.thindie.menogame2.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thindie.menogame2.R


private val light = Font(R.font.dsm, FontWeight.W300)
private val regular = Font(R.font.dsm, FontWeight.W400)
private val medium = Font(R.font.dsm, FontWeight.W500)
private val semibold = Font(R.font.dsm, FontWeight.W600)

private val MenoGame2Theme = FontFamily(fonts = listOf(light, regular, medium, semibold))


val TheMathGameTypo = Typography(
    displayLarge = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp
    ),
    displayMedium = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W400,
        fontSize = 60.sp
    ),
    displaySmall = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W600,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MenoGame2Theme,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)