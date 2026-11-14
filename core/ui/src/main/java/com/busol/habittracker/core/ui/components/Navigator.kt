package com.busol.habittracker.core.ui.components

import androidx.navigation.NavController
import androidx.navigation.NavOptions


interface Navigator {
    fun attach(naxvController: NavController)
    fun detach()
    fun navigate(route: String, navOptions: NavOptions? = null)
    fun navigateAndClearBackStack(route: String)
    fun pop()
    fun popBackStack(route: String, inclusive: Boolean = true)
}

class NavigatorImpl : Navigator {
    private var _navigator: NavController? = null
    private val navigator
        get() = requireNotNull(_navigator) { "attach navController" }

    override fun attach(navController: NavController) {
        _navigator = navController
    }

    override fun detach() {
        _navigator = null
    }

    override fun navigate(route: String, navOptions: NavOptions?) {
        navigator.navigate(route, navOptions)
    }

    override fun navigateAndClearBackStack(route: String) {
        navigator.navigate(route) {
            launchSingleTop = true
            navigator.graph.route?.let {
                popUpTo(it)
            }
        }
    }

    override fun pop() {
        navigator.popBackStack()
    }


    override fun popBackStack(route: String, inclusive: Boolean) {
        navigator.popBackStack(route, inclusive = inclusive)
    }
}