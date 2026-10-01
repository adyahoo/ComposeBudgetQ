package com.example.budgetlimittracking.ui.recordexpense

import com.example.budgetlimittracking.domain.model.Budget

data class RecordExpenseUiState(
    val title: String = "",
    val amountInput: String = "",
    val selectedBudget: Budget? = null,
    val budgets: List<Budget> = emptyList(),
    val currentBudgetLimit: Double = 0.0,
    val currentSpent: Double = 0.0,
    val projectedSpent: Double = 0.0,
    val isExceededWarning: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false
) {
    val parsedAmount: Double
        get() = amountInput.toDoubleOrNull() ?: 0.0

    val overageAmount: Double
        get() = (projectedSpent - currentBudgetLimit).coerceAtLeast(0.0)

    val remainingAmount: Double
        get() = (currentBudgetLimit - projectedSpent).coerceAtLeast(0.0)

    val capacityPercentage: Int
        get() = if (currentBudgetLimit > 0) ((projectedSpent / currentBudgetLimit) * 100).toInt() else 0
}
