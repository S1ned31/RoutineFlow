package com.busol.habittracker.core.database.vacation

import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.model.Vacation

interface VacationLocalDataSource {

    suspend fun getVacationsInPeriod(
        habitId: Long,
        period: LocalDateRange,
    ): List<Vacation>

    suspend fun getMultiHabitVacations(
        habitsToPeriods: List<Pair<List<Long>, LocalDateRange>>,
    ): Map<Long, List<Vacation>>

    suspend fun insertVacation(
        habitId: Long,
        vacation: Vacation,
    )
    suspend fun insertVacations(habitIdsToVacations: Map<Long, List<Vacation>>)
}