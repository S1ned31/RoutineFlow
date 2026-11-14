package com.example.auth.forgotPassword

data class ForgotPasswordState(
    val email: String = "",
    val isErrorEmail: Boolean = true,
    val isLoading: Boolean = false,
    val showSnackBar: Boolean = false,
)