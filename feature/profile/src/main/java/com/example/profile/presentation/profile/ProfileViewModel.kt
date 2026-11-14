package com.example.profile.presentation.profile

import com.busol.habittracker.core.domain.profile.GetProfileInfoUseCase
import com.busol.habittracker.core.domain.profile.SignOutUseCase
import com.busol.habittracker.core.ui.components.BaseViewModel
import com.busol.habittracker.core.ui.components.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val navigator: Navigator,
    private val signOutUseCase: SignOutUseCase,
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
) : BaseViewModel<ProfileState>() {
    init {
       update()
    }

    fun update() {
        launch {
            val user = getProfileInfoUseCase.execute()
            updateState {
                it.copy(
                    image = user.image,
                    nickname = user.name,
                    email = user.email
                )
            }
        }
    }

    override fun createInitialState(): ProfileState {
        return ProfileState()
    }

    fun signOut() {
        launch {
            signOutUseCase.execute()
            withContext(Dispatchers.Main) {
                navigator.navigateAndClearBackStack(SIGN_IN_ROUTE)
            }
        }
    }

    fun redactProfile() {
        navigator.navigate(REDACT_PROFILE_ROUTE)
    }

    fun navigate(route: String) {
        navigator.navigateAndClearBackStack(route)
    }
}

const val SIGN_IN_ROUTE = "SIGN_IN_ROUTE"
const val REDACT_PROFILE_ROUTE = "REDACT_PROFILE_ROUTE"