package com.busol.habittracker.core.domain.di

import com.busol.habittracker.core.data.habit.HabitRepository
import com.busol.habittracker.core.model.Habit
import org.koin.dsl.module

val habitDomainModule = module {
    single {
        InsertHabitUseCase(get<HabitRepository>()::insertHabit)
    }

    single {
        GetHabitUseCase(get<HabitRepository>()::getHabitById)
    }

    single {
        DeleteHabitUseCase(get<HabitRepository>()::deleteHabit)
    }
}

fun interface InsertHabitUseCase : suspend (Habit) -> Unit
fun interface GetHabitUseCase : suspend (Long) -> Habit
fun interface DeleteHabitUseCase : suspend (Long) -> Unit