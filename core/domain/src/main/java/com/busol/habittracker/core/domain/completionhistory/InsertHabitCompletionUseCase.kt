package com.busol.habittracker.core.domain.completionhistory

import com.busol.habittracker.core.model.Habit
import kotlinx.datetime.LocalDate

interface InsertHabitCompletionUseCase {
    suspend operator fun invoke(
        habitId: Long,
        completionRecord: Habit.CompletionRecord,
        today: LocalDate,
    )

    sealed class IllegalDateEditAttemptException : Exception() {
        class FutureDateEditAttemptException : IllegalDateEditAttemptException()
        class FinishedHabitDateEditAttemptException : IllegalDateEditAttemptException()
        class NotStartedHabitDateEditAttemptException : IllegalDateEditAttemptException()
    }
}