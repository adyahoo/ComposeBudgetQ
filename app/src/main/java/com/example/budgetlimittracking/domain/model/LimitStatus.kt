package com.example.budgetlimittracking.domain.model

enum class LimitStatus(val label: String) {
    ALL("All"),
    WITHIN_BUDGET("Within Budget"),
    NEAR_LIMIT("Near Limit"),
    EXCEEDED_LIMIT("Exceeded Limit")
}
