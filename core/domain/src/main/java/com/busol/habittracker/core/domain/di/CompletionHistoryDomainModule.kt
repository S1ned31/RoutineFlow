package com.busol.habittracker.core.domain.di

import com.busol.habittracker.core.domain.agenda.GetAgendaUseCase
import com.busol.habittracker.core.domain.agenda.GetAgendaUseCaseImpl
import com.busol.habittracker.core.domain.auth.FieldValidator
import com.busol.habittracker.core.domain.auth.ResetPasswordUseCase
import com.busol.habittracker.core.domain.auth.SignInUseCase
import com.busol.habittracker.core.domain.auth.SignUpUseCase
import com.busol.habittracker.core.domain.completiondata.GetHabitCompletionDataUseCase
import com.busol.habittracker.core.domain.completiondata.GetHabitCompletionDataUseCaseImpl
import com.busol.habittracker.core.domain.completionhistory.InsertHabitCompletionAndCashStreaks
import com.busol.habittracker.core.domain.completionhistory.InsertHabitCompletionUseCase
import com.busol.habittracker.core.domain.habitstatus.HabitStatusComputer
import com.busol.habittracker.core.domain.habitstatus.HabitStatusComputerImpl
import com.busol.habittracker.core.domain.info.GetPostByIdUseCase
import com.busol.habittracker.core.domain.info.GetPostListUseCase
import com.busol.habittracker.core.domain.profile.GetProfileInfoUseCase
import com.busol.habittracker.core.domain.profile.SaveProfileUseCase
import com.busol.habittracker.core.domain.profile.SignOutUseCase
import com.busol.habittracker.core.ui.components.Navigator
import com.busol.habittracker.core.ui.components.NavigatorImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val completionHistoryDomainModule = module {
    single<HabitStatusComputer> { HabitStatusComputerImpl() }

    single<InsertHabitCompletionUseCase> {
        InsertHabitCompletionAndCashStreaks(
            completionHistoryRepository = get(),
            getHabit = get(),
            streakManager = get(),
        )
    }

    single<GetHabitCompletionDataUseCase> {
        GetHabitCompletionDataUseCaseImpl(
            getHabit = get(),
            vacationRepository = get(),
            completionHistoryRepository = get(),
            habitStatusComputer = get(),
            defaultDispatcher = get(qualifier = named("defaultDispatcher")),
        )
    }

    single<GetAgendaUseCase> {
        GetAgendaUseCaseImpl(
            habitRepository = get(),
            vacationRepository = get(),
            completionHistoryRepository = get(),
            habitStatusComputer = get(),
        )
    }

    single<Navigator> {
        NavigatorImpl()
    }

    single {
        FieldValidator
    }

    factory {
        ResetPasswordUseCase(
            repository = get(),
        )
    }

    factory {
        SignInUseCase(
            repository = get(),
        )
    }

    factory {
        SignUpUseCase(
            repository = get(),
        )
    }

    factory {
        SignOutUseCase(
            repository = get()
        )
    }

    factory {
        GetProfileInfoUseCase(
            repository = get()
        )
    }

    factory {
        SaveProfileUseCase(
            repository = get(),
            navigator = get()
        )
    }

    factory {
        GetPostByIdUseCase(get())
    }

    factory {
        GetPostListUseCase(get())
    }
}