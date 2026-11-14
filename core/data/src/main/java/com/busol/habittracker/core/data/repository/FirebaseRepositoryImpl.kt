package com.busol.habittracker.core.data.repository

import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.busol.habittracker.core.model.CompleteResult
import kotlinx.coroutines.tasks.await


class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : FirebaseRepository {

    override suspend fun registerUser(name: String, email: String, password: String): CompleteResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: return CompleteResult(
                isSuccessful = false,
                errorMessage = "Error register user",
            )

            val userData = hashMapOf(
                "username" to name,
                "email" to email
            )

            firestore.collection("users").document(userId).set(userData).await()

            CompleteResult(isSuccessful = true)
        } catch (e: Exception) {
            e.printStackTrace()
            CompleteResult(isSuccessful = false, errorMessage = "Unknown error")
        }
    }

    override suspend fun signIn(email: String, password: String): CompleteResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            if (result.user == null) {
                CompleteResult(isSuccessful = false, errorMessage = "Error login")
            } else {
                CompleteResult(isSuccessful = true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            CompleteResult(isSuccessful = false, errorMessage = "Error login")
        }
    }

    override suspend fun signOut(): CompleteResult {
        return try {
            auth.signOut()
            CompleteResult(isSuccessful = true)
        } catch (e: Exception) {
            e.printStackTrace()
            CompleteResult(isSuccessful = false, errorMessage = "Error sign out")
        }
    }

    override suspend fun requestPassword(email: String): CompleteResult {
        return try {
            auth.sendPasswordResetEmail(email).await()
            CompleteResult(isSuccessful = true)
        } catch (e: Exception) {
            e.printStackTrace()
            CompleteResult(isSuccessful = false, errorMessage = "Error reset email")
        }
    }

    override suspend fun currentUser(): FirebaseUser? {
        val user = auth.currentUser
        return if (user != null) {
            val userDocument = firestore.collection("users").document(user.uid).get().await()
            val name = userDocument.getString("username") ?: ""
            val email = userDocument.getString("email") ?: ""
            val photo = userDocument.getString("image") ?: ""
            FirebaseUser(
                name = name,
                email = email,
                image = photo
            )
        } else null
    }

    override suspend fun updateInfo(nickname: String): CompleteResult {
        val user = auth.currentUser
        return if (user != null) {
            try {
                val updates = mapOf(
                    "username" to nickname,
                )

                firestore.collection("users").document(user.uid).update(updates).await()
                CompleteResult(isSuccessful = true)
            } catch (e: Exception) {
                e.printStackTrace()
                CompleteResult(isSuccessful = false, errorMessage = "Error updating user info")
            }
        } else {
            CompleteResult(isSuccessful = false, errorMessage = "User not authenticated")
        }
    }
}
