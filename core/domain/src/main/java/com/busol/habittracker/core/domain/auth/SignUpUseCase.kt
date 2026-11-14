package com.busol.habittracker.core.domain.auth

import com.busol.habittracker.core.data.repository.FirebaseRepository
import com.busol.habittracker.core.model.CompleteResult
import com.busol.habittracker.core.ui.components.UseCaseParams
import com.busol.habittracker.core.ui.components.UseCaseWithParams


class SignUpUseCase(
    private val repository: FirebaseRepository,
) : UseCaseWithParams<SignUpUseCase.SignUpUseCaseParams, CompleteResult> {

    class SignUpUseCaseParams(val username: String, val email: String, val password: String) : UseCaseParams
    override suspend fun invoke(params: SignUpUseCaseParams): CompleteResult {
        return repository.registerUser(
            email = params.email,
            name = params.username,
            password = params.password
        )
    }
}

