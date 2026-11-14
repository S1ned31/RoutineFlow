package com.busol.habittracker.core.data.di

import com.busol.habittracker.core.data.streaks.StreakRepository
import com.busol.habittracker.core.data.streaks.StreakRepositoryImpl
import org.koin.dsl.module

val streakDataModule = module {
    single<StreakRepository> {
        StreakRepositoryImpl(localDataSource = get())
    }
}