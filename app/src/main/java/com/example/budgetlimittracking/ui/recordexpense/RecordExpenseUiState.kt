package com.example.budgetlimittracking.ui.recordexpense

import com.example.budgetlimittracking.domain.model.BudgetCategory

data class RecordExpenseUiState(
    val title: String = "",
    val amountInput: String = "",
    val selectedCategory: BudgetCategory = BudgetCategory.FOOD_DINING,
    val currentBudgetLimit: Double = 0.0,
    val currentSpent: Double = 0.0,
    val projectedSpent: Double = 0.0,
    val isExceededWarning: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false
)
