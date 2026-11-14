package com.busol.habittracker.core.data.di

import com.busol.habittracker.core.data.habit.HabitRepository
import com.busol.habittracker.core.data.habit.HabitRepositoryImpl
import org.koin.dsl.module

val routineDataModule = module {

    single<HabitRepository> {
        HabitRepositoryImpl(localDataSource = get())
    }
}