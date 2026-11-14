package org.danylllllll.habit_tracker_kmp.domain.features.auth.signIn

data class SignInState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val isErrorEmail: Boolean = false,
    val isErrorPassword: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val isErrorPopUp: Boolean = false,
)