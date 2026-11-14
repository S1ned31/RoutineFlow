package com.busol.habittracker.core.domain.di

import com.busol.habittracker.core.domain.completiontime.GetHabitCompletionTimeUseCase
import com.busol.habittracker.core.domain.completiontime.GetHabitCompletionTimeUseCaseImpl
import org.koin.dsl.module

val completionTimeDomainModule = module {
    single<GetHabitCompletionTimeUseCase> {
        GetHabitCompletionTimeUseCaseImpl(
            getHabit = get(),
            dueDateSpecificCompletionTimeRepository = get(),
            completionTimeRepository = get(),
        )
    }
}