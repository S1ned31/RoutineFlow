package com.example.auth.signIn

import com.busol.habittracker.core.domain.auth.FieldValidator
import com.busol.habittracker.core.domain.auth.SignInUseCase
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.danylllllll.habit_tracker_kmp.domain.features.auth.signIn.SignInState

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val validator: FieldValidator,
    private val navigator: Navigator,
) : BaseViewModel<SignInState>() {
    override fun createInitialState(): SignInState {
        return SignInState()
    }

    fun onEmailChanged(email: String) {
        val isValidEmail = validator.validEmail(email)
        val isValidPassword = validator.validPassword(state.value.password)
        val isButtonEnabled = isValidEmail && isValidPassword
        updateState {
            it.copy(
                email = email,
                isErrorEmail = !isValidEmail,
                isButtonEnabled = isButtonEnabled,
            )
        }
    }

    fun onPasswordChanged(password: String) {
        val isValidEmail = validator.validEmail(state.value.email)
        val isValidPassword = validator.validPassword(password)
        val isButtonEnabled = isValidEmail && isValidPassword
        updateState {
            it.copy(
                password = password,
                isErrorPassword = !isValidPassword,
                isButtonEnabled = isButtonEnabled,
            )
        }
    }

    fun navigateToForgotPassword() {
        navigator.navigate(FORGOT_PASSWORD_ROUTE)
    }

    fun navigateToSignUp() {
        navigator.navigateAndClearBackStack(SIGN_UP_ROUTE)
    }

    fun signIn() {
        launch {
            val result = signInUseCase.invoke(
                SignInUseCase.SignInParams(
                    email = state.value.email,
                    password = state.value.password
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
