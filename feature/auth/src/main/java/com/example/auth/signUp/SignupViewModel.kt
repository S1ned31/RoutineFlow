package com.example.auth.signUp

import com.busol.habittracker.core.domain.auth.FieldValidator
import com.busol.habittracker.core.domain.auth.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val validator: FieldValidator,
    private val navigator: Navigator,
) : BaseViewModel<SignUpState>() {
    override fun createInitialState(): SignUpState {
        return SignUpState()
    }

    fun onEmailChanged(email: String) {
        val isEmailValid = validator.validEmail(email)
        val isPasswordValid = validator.validPassword(state.value.password)
        val isNicknameValid = validator.validName(state.value.username)
        val buttonEnabled = isEmailValid && isPasswordValid && isNicknameValid
        updateState {
            it.copy(
                email = email,
                isErrorEmail = !isEmailValid,
                isButtonEnabled = buttonEnabled
            )
        }
    }

    fun onPasswordChanged(password: String) {
        val isEmailValid = validator.validEmail(state.value.email)
        val isPasswordValid = validator.validPassword(password)
        val isNicknameValid = validator.validName(state.value.username)
        val buttonEnabled = isEmailValid && isPasswordValid && isNicknameValid
        updateState {
            it.copy(
                password = password,
                isErrorPassword = !isPasswordValid,
                isButtonEnabled = buttonEnabled
            )
        }
    }

    fun onUserNameChanged(username: String) {
        val isEmailValid = validator.validEmail(state.value.email)
        val isPasswordValid = validator.validPassword(state.value.password)
        val isNicknameValid = validator.validName(state.value.username)
        val buttonEnabled = isEmailValid && isPasswordValid && isNicknameValid
        updateState {
            it.copy(
                username = username,
                isUsernameError = !isNicknameValid,
                isButtonEnabled = buttonEnabled
            )
        }
    }

    fun navigateToSignIn() {
        navigator.navigateAndClearBackStack(SIGN_IN_ROUTE)
    }

    fun registerUser() {
        launch {
            val currentState = state.value
            val result = signUpUseCase.invoke(
                SignUpUseCase.SignUpUseCaseParams(
                    username = currentState.username,
                    email = currentState.email,
                    password = currentState.password
                )
            )
            if (result.isSuccessful) {
                withContext(Dispatchers.Main) {
                    navigator.navigateAndClearBackStack(AGENDA_NAV_ROUTE)
                }
            } else {
                updateState { it.copy(isErrorPopUp = true) }
            }
        }
    }

    fun changeVisibilityPassword() {
        updateState { it.copy(passwordVisibility = !state.value.passwordVisibility) }
    }

    fun hidePopUp() {
        updateState { it.copy(isErrorPopUp = false) }
    }
}

const val SIGN_IN_ROUTE = "SIGN_IN_ROUTE"
const val SIGN_UP_ROUTE = "SIGN_UP_ROUTE"
const val FORGOT_PASSWORD_ROUTE = "FORGOT_PASSWORD_ROUTE"
const val AGENDA_NAV_ROUTE = "agenda_route"