package com.busol.habittracker.core.domain.auth

import com.busol.habittracker.core.data.repository.FirebaseRepository
import com.busol.habittracker.core.model.CompleteResult
import com.busol.habittracker.core.ui.components.UseCaseParams
import com.busol.habittracker.core.ui.components.UseCaseWithParams

class SignInUseCase(
    private val repository: FirebaseRepository
) : UseCaseWithParams<SignInUseCase.SignInParams, CompleteResult> {
    class SignInParams(val email: String, val password: String) : UseCaseParams

    override suspend fun invoke(params: SignInParams): CompleteResult {
        return repository.signIn(
            email = params.email,
            password = params.password
        )
    }
}