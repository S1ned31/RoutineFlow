package com.busol.habittracker.core.domain.profile

import com.busol.habittracker.core.data.repository.FirebaseRepository

class SignOutUseCase(
    private val repository: FirebaseRepository,
){
    suspend fun execute() {
        repository.signOut()
    }
}