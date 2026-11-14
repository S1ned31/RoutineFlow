package com.example.auth.signUp

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val isErrorEmail: Boolean = false,
    val isErrorPassword: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val username: String = "",
    val isUsernameError: Boolean = false,
    val isLoading: Boolean = false,
    val isErrorPopUp: Boolean = false,
)