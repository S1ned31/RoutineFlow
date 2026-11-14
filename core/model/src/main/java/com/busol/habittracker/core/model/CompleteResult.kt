package com.busol.habittracker.core.model

data class CompleteResult(
    val isSuccessful: Boolean,
    val errorMessage: String? = null,
)