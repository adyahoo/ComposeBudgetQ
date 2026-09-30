package com.example.budgetlimittracking.domain.model

data class Budget(
    val id: String,
    val category: BudgetCategory,
    val cycle: BudgetCycle,
    val limitAmount: Double,
    val spentAmount: Double
) {
    val remainingAmount: Double
        get() = (limitAmount - spentAmount).coerceAtLeast(0.0)

    val usagePercentage: Float
        get() = if (limitAmount > 0) ((spentAmount / limitAmount) * 100).toFloat() else 0f

    val limitStatus: LimitStatus
        get() = when {
            spentAmount > limitAmount -> LimitStatus.EXCEEDED_LIMIT
            usagePercentage >= 80f -> LimitStatus.NEAR_LIMIT
            else -> LimitStatus.WITHIN_BUDGET
        }
}
