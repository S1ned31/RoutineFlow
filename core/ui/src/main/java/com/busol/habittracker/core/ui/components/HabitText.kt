package com.busol.habittracker.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.busol.habittracker.core.ui.theme.AppTheme


@Composable
fun HabitText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = AppTheme.fonts.h3,
    color: Color = AppTheme.colors.contentColor,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        textAlign = textAlign
    )
}