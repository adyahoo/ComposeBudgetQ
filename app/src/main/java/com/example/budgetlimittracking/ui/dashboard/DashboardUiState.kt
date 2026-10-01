package com.example.budgetlimittracking.ui.dashboard

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCycle
import com.example.budgetlimittracking.domain.model.LimitStatus

data class DashboardUiState(
    val selectedCycle: BudgetCycle = BudgetCycle.ALL,
    val budgets: List<Budget> = emptyList(),
    val totalLimit: Double = 0.0,
    val totalSpent: Double = 0.0,
    val isLoading: Boolean = false
) {
    val withinLimitCount: Int
        get() = budgets.count { it.limitStatus != LimitStatus.EXCEEDED_LIMIT }

    val exceededCount: Int
        get() = budgets.count { it.limitStatus == LimitStatus.EXCEEDED_LIMIT }

    val overallUsagePercentage: Float
        get() = if (totalLimit > 0) ((totalSpent / totalLimit) * 100).toFloat() else 0f

    val totalRemaining: Double
        get() = (totalLimit - totalSpent).coerceAtLeast(0.0)
}
