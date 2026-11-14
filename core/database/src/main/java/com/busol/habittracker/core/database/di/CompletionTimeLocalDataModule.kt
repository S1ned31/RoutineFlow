package com.busol.habittracker.core.database.di

import com.busol.habittracker.core.database.completiontime.DueDateSpecificCompletionTimeLocalDataSource
import com.busol.habittracker.core.database.completiontime.DueDateSpecificCompletionTimeLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val completionTimeLocalDataModule = module {
    single<DueDateSpecificCompletionTimeLocalDataSource> {
        DueDateSpecificCompletionTimeLocalDataSourceImpl(
            db = get(),
            ioDispatcher = get(qualifier = named("ioDispatcher")),
        )
    }
}