package com.busol.habittracker.core.ui.components.bottomBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.busol.habittracker.core.ui.R

class HabitBottomBarItems(
    private val homeTitle: String,
    private val feedTitle: String,
    private val profileTitle: String
) {
    val items
        @Composable get() = listOf(
            HabitBottomItem(
                text = homeTitle,
                route = "AGENDA_ROUTE",
                icon = ImageVector.vectorResource(R.drawable.book_open),
            ),
            HabitBottomItem(
                text = feedTitle,
                route = "ARTICLES_ROUTE",
                icon = ImageVector.vectorResource(R.drawable.feed),
            ),
            HabitBottomItem(
                text = profileTitle,
                route = "PROFILE_ROUTE",
                icon = ImageVector.vectorResource(R.drawable.user),
            )
        )
}