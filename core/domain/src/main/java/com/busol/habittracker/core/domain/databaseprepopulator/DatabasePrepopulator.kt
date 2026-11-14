package com.busol.habittracker.core.domain.databaseprepopulator

interface DatabasePrepopulator {
    suspend fun prepopulateDatabase(numOfHabits: Int = 50)
}