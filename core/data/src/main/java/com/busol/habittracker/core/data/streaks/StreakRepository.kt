package com.busol.habittracker.core.data.streaks

import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.model.Streak

interface StreakRepository {

    suspend fun insertStreaks(streaks: Map<Long, List<Streak>>)

    suspend fun upsertStreaks(
        habitId: Long,
        period: LocalDateRange,
        streaks: List<Streak>,
    )

    suspend fun getLastStreak(habitId: Long): Streak?
    suspend fun getLongestStreaks(habitId: Long): List<Streak>
}