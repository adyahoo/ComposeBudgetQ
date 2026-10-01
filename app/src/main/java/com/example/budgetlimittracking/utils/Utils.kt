package com.example.budgetlimittracking.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {
    fun getTodayFormatted(
        pattern: String = "d MMM yyyy",
        locale: Locale = Locale.getDefault()
    ): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)

        return today.format(formatter)
    }
}