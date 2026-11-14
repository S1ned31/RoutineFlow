package com.busol.habittracker.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.busol.habittracker.core.ui.R
import com.busol.habittracker.core.ui.theme.AppTheme

@Composable
fun ErrorPopUp(
    title: String,
    message: String,
    buttonText: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {})
            .background(AppTheme.colors.contentColor.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(AppTheme.colors.containerColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.padding(vertical = 10.dp).size(40.dp),
                imageVector = ImageVector.vectorResource(R.drawable.alert_circle),
                contentDescription = null,
                tint = AppTheme.colors.error
            )
            HabitText(
                modifier = Modifier.padding(10.dp),
                text = title,
                style = AppTheme.fonts.h1,
                color = AppTheme.colors.divider
            )
            Spacer(modifier = Modifier.height(10.dp))
            HabitText(
                modifier = Modifier.padding(10.dp),
                text = message,
                style = AppTheme.fonts.h3,
                color = AppTheme.colors.contentColor
            )

            HabitButton(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                onClick = onClick,
                text = buttonText
            )
        }
    }
}