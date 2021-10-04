package com.android.ravn.dargueta.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PeopleOfStarWarsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content
    )
}

private val LightColors = lightColors(
    primary = Black,
    primaryVariant = Black,
    onPrimary = Color.White,
    secondary = DarkGray,
    secondaryVariant = LightGray,
    onSecondary = Color.Black
)

private val DarkColors = darkColors(
    primary = DarkGray,
    primaryVariant = Black,
    onPrimary = Color.Black,
    secondary = LightGray,
    secondaryVariant = LightGray,
    onSecondary = Color.Black
)