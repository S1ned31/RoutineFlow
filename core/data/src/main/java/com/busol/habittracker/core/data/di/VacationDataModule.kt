package com.busol.habittracker.core.data.di

import com.busol.habittracker.core.data.vacation.VacationRepository
import com.busol.habittracker.core.data.vacation.VacationRepositoryImpl
import com.busol.habittracker.core.database.vacation.VacationLocalDataSource
import com.busol.habittracker.core.database.vacation.VacationLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val vacationDataModule = module {
    single<VacationLocalDataSource> {
        VacationLocalDataSourceImpl(
            db = get(),
            ioDispatcher = get(qualifier = named("ioDispatcher")),
        )
    }
    single<VacationRepository> {
        VacationRepositoryImpl(
            localDataSource = get(),
        )
    }
}