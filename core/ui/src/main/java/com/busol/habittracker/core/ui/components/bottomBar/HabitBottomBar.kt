package com.busol.habittracker.core.ui.components.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.busol.habittracker.core.ui.theme.AppTheme


@Composable
fun HabitBottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.colors.background,
    bottomBarItems: List<HabitBottomItem>,
    currentEntry: NavBackStackEntry?,
    navigate: (String) -> Unit,
) {
    val borderColor = AppTheme.colors.borderColors
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AppTheme.dimensions.bottomBarSize)
            .background(containerColor)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = strokeWidth / 2
                drawLine(
                    color = borderColor,
                    start = Offset(x = 0f, y = y),
                    end = Offset(x = size.width, y = y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        HabitBottomRowList(
            items = bottomBarItems,
            navigate = navigate,
            currentEntry = currentEntry
        )
    }
}
