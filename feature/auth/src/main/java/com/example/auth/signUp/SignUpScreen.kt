package com.example.auth.signUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.busol.habittracker.core.ui.components.BaseScreen
import com.busol.habittracker.core.ui.components.ErrorPopUp
import com.busol.habittracker.core.ui.components.HabitButton
import com.busol.habittracker.core.ui.components.HabitText
import com.busol.habittracker.core.ui.components.HabitTextField
import com.busol.habittracker.core.ui.theme.AppTheme
import com.busol.habittracker.feature.routinedetails.R

@Composable
fun SignUpScreen() {
    BaseScreen<SignUpViewModel> { viewModel ->
        val state by viewModel.state.collectAsState()

        SignUpContent(
            state = state,
            navigateToSignIn = viewModel::navigateToSignIn,
            register = viewModel::registerUser,
            onNicknameChanged = viewModel::onUserNameChanged,
            onEmailChanged = viewModel::onEmailChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            changeVisibilityPassword = viewModel::changeVisibilityPassword,
            hidePopUp = viewModel::hidePopUp,
        )
    }
}


@Composable
fun SignUpContent(
    state: SignUpState,
    register: () -> Unit,
    onNicknameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    navigateToSignIn: () -> Unit,
    changeVisibilityPassword: () -> Unit,
    hidePopUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HabitText(
            modifier = Modifier.padding(vertical = 20.dp),
            text = stringResource(R.string.sign_up),
            style = AppTheme.fonts.h1
        )
        HabitTextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Email),
            text = state.username,
            onChangedText = onNicknameChanged,
            isError = state.isUsernameError,
            label = {
                if (state.isUsernameError) {
                    HabitText(
                        text = stringResource(R.string.nickname_error),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.error,
                    )
                } else {
                    HabitText(
                        text = stringResource(R.string.nickname),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.accent,
                    )
                }
            },
        )

        Spacer(Modifier.height(20.dp))

        HabitTextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Email),
            text = state.email,
            onChangedText = onEmailChanged,
            isError = state.isErrorEmail,
            label = {
                if (state.isErrorEmail) {
                    HabitText(
                        text = stringResource(R.string.error_email),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.error
                    )
                } else {
                    HabitText(
                        text = stringResource(R.string.email),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.accent
                    )
                }
            }
        )

        Spacer(Modifier.height(20.dp))

        HabitTextField(
            text = state.password,
            onChangedText = onPasswordChanged,
            modifier = Modifier.fillMaxWidth(),
            isError = state.isErrorPassword,
            label = {
                if (state.isErrorPassword) {
                    HabitText(
                        text = stringResource(R.string.error_password),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.error
                    )
                } else {
                    HabitText(
                        text = stringResource(R.string.password),
                        style = AppTheme.fonts.label,
                        color = AppTheme.colors.accent
                    )
                }
            },
            leadingIcon = {
                Box(modifier = Modifier.clickable { changeVisibilityPassword() }) {
                    if(state.passwordVisibility) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.visible),
                            contentDescription = null
                        )
                    } else{
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.invisible),
                            contentDescription = null
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Password),
            visualTransformation = if (state.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(Modifier.height(10.dp))

        HabitButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            onClick = register,
            text = stringResource(R.string.sign_up),
            colorText = AppTheme.colors.background
        )

        Spacer(Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            HabitText(
                modifier = Modifier.clickable {
                    navigateToSignIn()
                },
                text = stringResource(R.string.sign_in),
                style = AppTheme.fonts.h2
            )
        }
    }
    if (state.isErrorPopUp) {
        ErrorPopUp(
            title = stringResource(R.string.something_went_wrong),
            message = stringResource(R.string.error_with_signing_up),
            onClick = hidePopUp,
            buttonText = stringResource(R.string.ok)
        )
    }
}