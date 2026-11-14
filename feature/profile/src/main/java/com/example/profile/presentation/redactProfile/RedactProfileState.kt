package com.example.profile.presentation.redactProfile

import android.net.Uri

data class RedactProfileState(
    val nickname: String = "",
    val image: Uri? = null,
)