package com.busol.habittracker.core.ui.components.bottomBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.busol.habittracker.core.ui.components.HabitText
import com.busol.habittracker.core.ui.theme.AppTheme

@Composable
fun HabitBottomRowItem(
    modifier: Modifier = Modifier,
    bottomItem: HabitBottomItem,
    onClick: (String) -> Unit,
    isSelected: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !isSelected
            ) {
                onClick(bottomItem.route)
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier.wrapContentSize(),
        ) {
            Icon(
                imageVector = bottomItem.icon,
                contentDescription = null,
                tint = AppTheme.colors.contentColor
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        HabitText(
            text = bottomItem.text,
            color = AppTheme.colors.contentColor,
            style = AppTheme.fonts.label,
        )
    }
}