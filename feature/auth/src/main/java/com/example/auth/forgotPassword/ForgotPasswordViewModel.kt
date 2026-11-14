package com.example.auth.forgotPassword

import com.busol.habittracker.core.domain.auth.FieldValidator
import com.busol.habittracker.core.domain.auth.ResetPasswordUseCase
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator

class ForgotPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val validator: FieldValidator,
    private val navigator: Navigator
) : BaseViewModel<ForgotPasswordState>() {
    override fun createInitialState(): ForgotPasswordState {
        return ForgotPasswordState()
    }

    fun onChangedEmail(email: String) {
        val isErrorEmail = !validator.validEmail(email)
        updateState {
            it.copy(email = email, isErrorEmail = isErrorEmail)
        }
    }

    fun sendToEmail() {
        launch {
            val result = resetPasswordUseCase.invoke(
                ResetPasswordUseCase.ResetPasswordParams(
                    email = state.value.email
                )
            )
            if (result.isSuccessful) {
                updateState { it.copy(showSnackBar = true) }
            }
        }
    }

    fun navigateToSignIn() {
        navigator.navigateAndClearBackStack(SIGN_IN_ROUTE)
    }
}

const val SIGN_IN_ROUTE = "SIGN_IN_ROUTE"
const val SIGN_UP_ROUTE = "SIGN_UP_ROUTE"
const val FORGOT_PASSWORD_ROUTE = "FORGOT_PASSWORD_ROUTE"
const val AGENDA_NAV_ROUTE = "agenda_route"