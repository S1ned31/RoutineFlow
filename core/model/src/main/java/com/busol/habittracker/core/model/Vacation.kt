package com.busol.habittracker.core.model

import kotlinx.datetime.LocalDate

data class Vacation(
    val startDate: LocalDate,
    val endDate: LocalDate?,
) {
    fun containsDate(date: LocalDate): Boolean = date >= startDate && (endDate == null || date <= endDate)
}