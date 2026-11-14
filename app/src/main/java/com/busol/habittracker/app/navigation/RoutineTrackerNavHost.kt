package com.busol.habittracker.app.navigation

import com.example.articles.postList.PostListScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.articles.postInfo.PostInfoScreen
import com.example.auth.forgotPassword.ForgotPasswordScreen
import com.example.auth.signIn.SignInScreen
import com.example.auth.signUp.SignUpScreen
import com.example.profile.presentation.profile.ProfileScreen
import com.example.profile.presentation.redactProfile.RedactProfileScreen
import com.busol.habittracker.addeditroutine.navigation.addRoutineScreen
import com.busol.habittracker.addeditroutine.navigation.navigateToAddRoutine
import com.busol.habittracker.core.ui.components.Navigator
import com.busol.habittracker.feature.agenda.navigation.AGENDA_NAV_ROUTE
import com.busol.habittracker.feature.agenda.navigation.agendaScreen
import com.busol.habittracker.feature.agenda.navigation.navigateToAgenda
import com.busol.habittracker.routinedetails.navigation.navigateToRoutineDetails
import com.busol.habittracker.routinedetails.navigation.routineDetailsScreen
import org.koin.java.KoinJavaComponent.inject

@Composable
fun RoutineTrackerNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    val navigator: Navigator by inject(Navigator::class.java)
    val navController = rememberNavController()
    navigator.attach(navController)
    DisposableEffect(Unit) {
        onDispose {
            navigator.detach()
        }
    }
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AGENDA_NAV_ROUTE,
            modifier = modifier.padding(innerPadding),
        ) {
            addRoutineScreen(
                navigateBackAndRecreate = {
                    navController.navigateToAgenda {
                        popUpTo(route = AGENDA_NAV_ROUTE) { inclusive = true }
                    }
                },
                navigateBack = {
                    navController.navigateUp()
                },
            )
            agendaScreen(
                onRoutineClick = {
                    navController.navigateToRoutineDetails(routineId = it) {
                        launchSingleTop = true
                    }
                },
                onAddRoutineClick = { navController.navigateToAddRoutine() },
            )
            routineDetailsScreen(
                popBackStack = {
                    navController.navigateToAgenda {
                        popUpTo(route = AGENDA_NAV_ROUTE) { inclusive = true }
                    }
                },
            )
            composable(SIGN_IN_ROUTE) {
                SignInScreen()
            }

            composable(SIGN_UP_ROUTE) {
                SignUpScreen()
            }

            composable(FORGOT_PASSWORD_ROUTE) {
                ForgotPasswordScreen()
            }

            composable(PROFILE_ROUTE) {
                ProfileScreen()
            }

            composable(REDACT_PROFILE_ROUTE) {
                RedactProfileScreen()
            }
            composable(ARTICLES_ROUTE) {
                PostListScreen()
            }
            composable(
                route = "$ARTICLE_ROUTE/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType }),
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong("id")
                if (id != null) {
                    PostInfoScreen(id)
                }
            }
        }
    }
}

const val SIGN_IN_ROUTE = "SIGN_IN_ROUTE"
const val SIGN_UP_ROUTE = "SIGN_UP_ROUTE"
const val FORGOT_PASSWORD_ROUTE = "FORGOT_PASSWORD_ROUTE"
const val PROFILE_ROUTE = "PROFILE_ROUTE"
const val REDACT_PROFILE_ROUTE = "REDACT_PROFILE_ROUTE"
const val ARTICLES_ROUTE = "ARTICLES_ROUTE"
const val ARTICLE_ROUTE = "ARTICLE_ROUTE"