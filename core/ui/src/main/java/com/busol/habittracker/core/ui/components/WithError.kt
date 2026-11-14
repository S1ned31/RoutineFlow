package com.busol.habittracker.core.ui.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface WithError {

    val error: StateFlow<ErrorData?>

    fun showError(error: ErrorData)
    fun onError(action: String)
    fun clearErrors()

    private class WithErrorImpl : WithError {

        override val error = MutableStateFlow<ErrorData?>(null)

        override fun showError(error: ErrorData) {
            this.error.value = error
        }

        override fun onError(action: String) {
            clearErrors()
        }

        override fun clearErrors() {
            error.value = null
        }

    }

    companion object {

        fun default(): WithError {
            return WithErrorImpl()
        }

    }

}