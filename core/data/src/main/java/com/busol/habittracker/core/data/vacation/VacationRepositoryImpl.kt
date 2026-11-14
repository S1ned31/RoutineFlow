package com.busol.habittracker.core.data.vacation

import com.busol.habittracker.core.database.vacation.VacationLocalDataSource
import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.model.Vacation

class VacationRepositoryImpl(
    private val localDataSource: VacationLocalDataSource,
) : VacationRepository {

    override suspend fun getVacationsInPeriod(
        habitId: Long,
        period: LocalDateRange,
    ): List<Vacation> = localDataSource.getVacationsInPeriod(habitId, period)

    override suspend fun getMultiHabitVacations(
        habitsToPeriods: List<Pair<List<Long>, LocalDateRange>>,
    ): Map<Long, List<Vacation>> = localDataSource.getMultiHabitVacations(habitsToPeriods)

    override suspend fun insertVacation(
        habitId: Long,
        vacation: Vacation,
    ) = localDataSource.insertVacation(habitId, vacation)

    override suspend fun insertVacations(habitIdsToVacations: Map<Long, List<Vacation>>) =
        localDataSource.insertVacations(habitIdsToVacations)
}