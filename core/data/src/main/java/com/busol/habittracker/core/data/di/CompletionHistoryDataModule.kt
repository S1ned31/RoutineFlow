package com.busol.habittracker.core.data.di

import com.busol.habittracker.core.data.completionhistory.CompletionHistoryRepository
import com.busol.habittracker.core.data.completionhistory.CompletionHistoryRepositoryImpl
import com.busol.habittracker.core.database.completionhistory.CompletionHistoryLocalDataSource
import com.busol.habittracker.core.database.completionhistory.CompletionHistoryLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val completionHistoryDataModule = module {
    single<CompletionHistoryLocalDataSource> {
        CompletionHistoryLocalDataSourceImpl(
            db = get(),
            ioDispatcher = get(qualifier = named("ioDispatcher")),
        )
    }

    single<CompletionHistoryRepository> {
        CompletionHistoryRepositoryImpl(localDataSource = get())
    }
}