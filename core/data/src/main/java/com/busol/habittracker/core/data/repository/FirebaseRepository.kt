package com.busol.habittracker.core.data.repository

import com.busol.habittracker.core.model.CompleteResult

interface FirebaseRepository {
    suspend fun registerUser(name: String, email: String, password: String): CompleteResult
    suspend fun signIn(email: String, password: String): CompleteResult
    suspend fun signOut(): CompleteResult
    suspend fun requestPassword(email: String): CompleteResult
    suspend fun currentUser() : FirebaseUser?
    suspend fun updateInfo(nickname: String): CompleteResult
}

class FirebaseUser(
    val image: String,
    val name: String,
    val email: String,
)