package com.example.profile.di

import com.example.profile.presentation.profile.ProfileViewModel
import com.example.profile.presentation.redactProfile.RedactProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel {
        ProfileViewModel(
            navigator = get(),
            signOutUseCase = get(),
            getProfileInfoUseCase = get(),
        )
    }

    viewModel {
        RedactProfileViewModel(
            navigator = get(),
            saveProfileUseCase = get(),
        )
    }
}