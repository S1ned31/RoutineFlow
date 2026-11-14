package com.busol.habittracker.feature.agenda.di

import com.busol.habittracker.feature.agenda.AgendaScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val agendaScreenModule = module {

    viewModel {
        AgendaScreenViewModel(
            insertHabitCompletion = get(),
            getAgenda = get(),
            navigator = get()
        )
    }
}