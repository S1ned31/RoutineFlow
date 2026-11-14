package com.busol.habittracker.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.busol.habittracker.core.ui.theme.AppTheme

@Composable
fun HabitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    colorText: Color = AppTheme.colors.background
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = AppTheme.colors.buttonColors
    ) {
        HabitText(text = text, color = colorText)
    }
}


@Composable
fun HabitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = AppTheme.colors.buttonColors
    ) {
        text()
    }
}