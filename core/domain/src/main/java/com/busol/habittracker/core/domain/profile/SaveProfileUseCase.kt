package com.busol.habittracker.core.domain.profile

import com.busol.habittracker.core.data.repository.FirebaseRepository
import com.busol.habittracker.core.ui.components.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveProfileUseCase(
    private val repository: FirebaseRepository,
    private val navigator: Navigator,
) {
    suspend fun execute(name: String) {
        repository.updateInfo(name)
        withContext(Dispatchers.Main) {
            navigator.pop()
        }
    }
}