package com.busol.habittracker.routinedetails.di

import com.busol.habittracker.routinedetails.RoutineDetailsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val routineDetailsModule = module {
    viewModel { parameters ->
        RoutineDetailsScreenViewModel(
            routineId = parameters.get(),
            getHabit = get(),
            getHabitCompletionData = get(),
            insertHabitCompletion = get(),
            deleteHabit = get(),
            getCurrentStreak = get(),
            getLongestStreak = get(),
            getStreaksInPeriod = get(),
        )
    }
}