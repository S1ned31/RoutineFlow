package com.busol.habittracker.core.domain.completiontime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

interface GetHabitCompletionTimeUseCase {
    suspend operator fun invoke(
        habitId: Long,
        date: LocalDate,
    ): LocalTime?
}