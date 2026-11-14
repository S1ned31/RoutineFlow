package com.busol.habittracker.core.domain.completiondata

import com.busol.habittracker.core.logic.time.LocalDateRange
import com.busol.habittracker.core.model.HabitCompletionData
import kotlinx.datetime.LocalDate

interface GetHabitCompletionDataUseCase {
    suspend operator fun invoke(
        habitId: Long,
        validationDate: LocalDate,
        today: LocalDate,
    ): HabitCompletionData

    suspend operator fun invoke(
        habitId: Long,
        validationDates: LocalDateRange,
        today: LocalDate,
    ): Map<LocalDate, HabitCompletionData>
}