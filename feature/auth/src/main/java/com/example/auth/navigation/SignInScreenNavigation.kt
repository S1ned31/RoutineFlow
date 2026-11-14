package com.example.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable

const val AUTH_HOST = "AUTH_HOST"

fun NavGraphBuilder.authGraph() {
    composable(AUTH_HOST) {
//        AuthScreen()
    }
}

fun NavController.navigateToAuthScreen(navOptionsBuilder: NavOptionsBuilder.() -> Unit = {}) {
    this.navigate(AUTH_HOST, navOptionsBuilder)
}
