package com.busol.habittracker.feature.agenda.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.busol.habittracker.feature.agenda.AgendaRoute

const val AGENDA_NAV_ROUTE = "AGENDA_ROUTE"

fun NavController.navigateToAgenda(navOptionsBuilder: NavOptionsBuilder.() -> Unit = {}) {
    this.navigate(AGENDA_NAV_ROUTE, navOptionsBuilder)
}

fun NavGraphBuilder.agendaScreen(
    onRoutineClick: (Long) -> Unit,
    onAddRoutineClick: () -> Unit,
) {
    composable(
        route = AGENDA_NAV_ROUTE,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        AgendaRoute(
            onRoutineClick = onRoutineClick,
            onAddRoutineClick = onAddRoutineClick,
        )
    }
}
