package com.example.budgetlimittracking.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toDotDecimalString(): String {
    val localeID = Locale("id", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID).apply {
        maximumFractionDigits = 0
    }

    return numberFormat.format(this)
}