package com.busol.habittracker.core.domain.databaseprepopulator

class DatabasePrepopulatorNoOp : DatabasePrepopulator {
    override suspend fun prepopulateDatabase(numOfHabits: Int) {
        // No-op
    }
}