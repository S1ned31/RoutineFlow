package com.busol.habittracker.core.domain.databaseprepopulator

import org.koin.dsl.module

val databasePrepopulatorModule = module {
    single<DatabasePrepopulator> {
        DatabasePrepopulatorNoOp()
    }
}