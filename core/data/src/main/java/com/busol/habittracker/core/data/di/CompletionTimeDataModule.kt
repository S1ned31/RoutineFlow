package com.busol.habittracker.core.data.di

import com.busol.habittracker.core.data.completiontime.CompletionTimeRepository
import com.busol.habittracker.core.data.completiontime.CompletionTimeRepositoryImpl
import com.busol.habittracker.core.database.completiontime.CompletionTimeLocalDataSource
import com.busol.habittracker.core.database.completiontime.CompletionTimeLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val completionTimeDataModule = module {

    single<CompletionTimeLocalDataSource> {
        CompletionTimeLocalDataSourceImpl(
            db = get(),
            ioDispatcher = get(qualifier = named("ioDispatcher")),
        )
    }

    single<CompletionTimeRepository> {
        CompletionTimeRepositoryImpl(localDataSource = get())
    }
}