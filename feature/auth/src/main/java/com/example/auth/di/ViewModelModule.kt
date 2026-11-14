package com.example.auth.di

import com.example.auth.forgotPassword.ForgotPasswordViewModel
import com.example.auth.signIn.SignInViewModel
import com.example.auth.signUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ForgotPasswordViewModel(
            resetPasswordUseCase = get(),
            validator = get(),
            navigator = get(),
        )
    }

    viewModel {
        SignInViewModel(
            signInUseCase = get(),
            validator = get(),
            navigator = get(),
        )
    }
    viewModel {
        SignUpViewModel(
            signUpUseCase = get(),
            validator = get(),
            navigator = get(),
        )
    }
}