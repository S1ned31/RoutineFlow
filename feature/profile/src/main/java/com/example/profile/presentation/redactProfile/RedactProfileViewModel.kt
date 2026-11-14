package com.example.profile.presentation.redactProfile

import android.net.Uri
import com.busol.habittracker.core.domain.profile.SaveProfileUseCase
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator

class RedactProfileViewModel(
    private val navigator: Navigator,
    private val saveProfileUseCase: SaveProfileUseCase,
) : BaseViewModel<RedactProfileState>() {
    override fun createInitialState(): RedactProfileState {
        return RedactProfileState()
    }

    fun onNameChanged(name: String) {
        updateState { it.copy(nickname = name) }
    }

    fun onSave() {
        launch {
            val state = state.value
            saveProfileUseCase.execute(name = state.nickname)
        }
    }
}