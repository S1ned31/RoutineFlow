package com.busol.habittracker.core.data.streaks

import com.busol.habittracker.core.database.streak.StreakLocalDataSource
import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.model.Streak

class StreakRepositoryImpl(
    private val localDataSource: StreakLocalDataSource,
) : StreakRepository {

    override suspend fun insertStreaks(streaks: Map<Long, List<Streak>>) = localDataSource.insertStreaks(streaks)

    override suspend fun upsertStreaks(
        habitId: Long,
        period: LocalDateRange,
        streaks: List<Streak>,
    ) = localDataSource.upsertStreaks(habitId, period, streaks)

    override suspend fun getLastStreak(habitId: Long): Streak? = localDataSource.getLastStreak(habitId)
    override suspend fun getLongestStreaks(habitId: Long): List<Streak> = localDataSource.getLongestStreaks(habitId)
}