package com.busol.habittracker.core.domain.profile

import com.busol.habittracker.core.data.repository.FirebaseRepository

class GetProfileInfoUseCase(
    private val repository: FirebaseRepository,
) {
    suspend fun execute(): User {
        val user = repository.currentUser()

        val currentUser = if (user != null) {
            User(
                image = user.image,
                name = user.name,
                email = user.email,
            )
        } else {
            User(
                image = "",
                name = "",
                email = "",
            )
        }
        return currentUser
    }
}

class User(val image: String, val name: String, val email: String)