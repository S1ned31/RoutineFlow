package com.busol.habittracker.core.domain.auth

import com.busol.habittracker.core.data.repository.FirebaseRepository
import com.busol.habittracker.core.model.CompleteResult
import com.busol.habittracker.core.ui.components.UseCaseParams
import com.busol.habittracker.core.ui.components.UseCaseWithParams

class ResetPasswordUseCase(
    private val repository: FirebaseRepository
) : UseCaseWithParams<ResetPasswordUseCase.ResetPasswordParams, CompleteResult> {
    class ResetPasswordParams(val email: String) : UseCaseParams

    override suspend fun invoke(params: ResetPasswordParams): CompleteResult {
        return repository.requestPassword(
            email = params.email
        )
    }
}