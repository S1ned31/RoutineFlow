package com.busol.habittracker.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.busol.habittracker.app.navigation.RoutineTrackerNavHost
import com.busol.habittracker.app.navigation.SIGN_IN_ROUTE
import com.busol.habittracker.core.ui.theme.RoutineTrackerTheme
import com.busol.habittracker.feature.agenda.navigation.AGENDA_NAV_ROUTE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= 29) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            val useDarkTheme = isSystemInDarkTheme()

            RoutineTrackerTheme(
                useDarkTheme = useDarkTheme,
                disableDynamicColor = false,
            ) {
                RoutineTrackerNavHost(
                    modifier = Modifier.fillMaxSize(),
                    startDestination = SIGN_IN_ROUTE,
                )
            }
        }
    }
}