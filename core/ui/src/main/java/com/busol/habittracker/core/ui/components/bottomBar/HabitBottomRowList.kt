package com.busol.habittracker.core.ui.components.bottomBar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry

@SuppressLint("RestrictedApi")
@Composable
fun HabitBottomRowList(
    modifier: Modifier = Modifier,
    items: List<HabitBottomItem>,
    navigate: (String) -> Unit,
    currentEntry: NavBackStackEntry?,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        items.forEach { item ->
            val isSelected by remember(currentEntry) {
                derivedStateOf {
                    currentEntry?.destination?.hasRoute(item.route, arguments = null) == true
                }
            }
            HabitBottomRowItem(
                bottomItem = item,
                onClick = navigate,
                isSelected = isSelected,
                modifier = Modifier.weight(1f / items.size.toFloat())
            )
        }
    }
}