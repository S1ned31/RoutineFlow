package com.busol.habittracker.core.domain.auth

object FieldValidator {
    fun validEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return email.matches(emailRegex)
    }

    fun validPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$")
        return password.matches(passwordRegex)
    }

    fun validName(name: String): Boolean {
        val nameRegex = Regex("^[A-Za-z]{3,}$")
        return name.matches(nameRegex)
    }
}