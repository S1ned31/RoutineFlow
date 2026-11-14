package com.busol.habittracker.core.database.di

import com.busol.habittracker.core.database.streak.StreakLocalDataSource
import com.busol.habittracker.core.database.streak.StreakLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val streakLocalDataModule = module {
    single<StreakLocalDataSource> {
        StreakLocalDataSourceImpl(
            db = get(),
            ioDispatcher = get(qualifier = named("ioDispatcher")),
        )
    }
}