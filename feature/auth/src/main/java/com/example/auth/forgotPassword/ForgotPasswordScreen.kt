package com.example.auth.forgotPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.busol.habittracker.core.ui.components.HabitButton
import com.busol.habittracker.core.ui.components.HabitText
import com.busol.habittracker.core.ui.components.HabitTextField
import com.busol.habittracker.core.ui.theme.AppTheme
import com.busol.habittracker.feature.routinedetails.R
import com.busol.habittracker.core.ui.components.BaseScreen

@Composable
fun ForgotPasswordScreen() {
    BaseScreen<ForgotPasswordViewModel> { viewModel ->
        val state by viewModel.state.collectAsState()

        ForgotPasswordContent(
            state = state,
            onEmailChanged = viewModel::onChangedEmail,
            sendToEmail = viewModel::sendToEmail,
            navigateToSignIn = viewModel::navigateToSignIn,
        )
    }
}

@Composable
fun ForgotPasswordContent(
    state: ForgotPasswordState,
    navigateToSignIn: () -> Unit,
    onEmailChanged: (String) -> Unit,
    sendToEmail: () -> Unit,
) {
    val snackBarState = remember {
        SnackbarHostState()
    }
    val snackBarMessage = stringResource(R.string.check_your_email)
    LaunchedEffect(state.showSnackBar) {
        if (state.showSnackBar) {
            snackBarState.showSnackbar(snackBarMessage)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HabitText(
            text = stringResource(R.string.forgot_password),
            style = AppTheme.fonts.h1,
        )
        Spacer(Modifier.height(10.dp))
        HabitText(
            text = stringResource(R.string.write_your_email_for_reset),
            style = AppTheme.fonts.h3,
        )

        HabitTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.email,
            onChangedText = onEmailChanged,
            isError = state.isErrorEmail,
            label = {
                if (state.isErrorEmail) {
                    HabitText(
                        text = stringResource(R.string.error_email),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.error,
                    )
                } else {
                    HabitText(
                        text = stringResource(R.string.email),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.contentColor,
                    )
                }
            },
        )

        HabitButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            text = stringResource(R.string.send_reset_to_email),
            onClick = sendToEmail,
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        SnackbarHost(hostState = snackBarState) {
            Snackbar(
                action = {
                    HabitButton(
                        text = "OK",
                        onClick = { navigateToSignIn() },
                    )
                },
                modifier = Modifier.padding(16.dp),
                content = {
                    HabitText(text = snackBarMessage, color = AppTheme.colors.background)
                },
            )
        }
    }
}
