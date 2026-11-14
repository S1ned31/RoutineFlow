package com.busol.habittracker.core.database.model

import com.busol.habittracker.core.database.CachedStreakEntity
import com.busol.habittracker.core.model.Streak

fun CachedStreakEntity.toExternalModel() = Streak(
    startDate = startDate,
    endDate = endDate,
)