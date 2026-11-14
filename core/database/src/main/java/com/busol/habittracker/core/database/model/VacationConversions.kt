package com.busol.habittracker.core.database.model

import com.busol.habittracker.core.database.VacationEntity
import com.busol.habittracker.core.model.Vacation

fun VacationEntity.toExternalModel() = Vacation(
    startDate = startDate,
    endDate = endDate,
)