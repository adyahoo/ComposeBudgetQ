package com.example.budgetlimittracking.ui.addeditbudget

import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.BudgetCycle

data class AddEditBudgetUiState(
    val budgetId: String? = null,
    val category: BudgetCategory = BudgetCategory.FOOD_DINING,
    val customCategoryName: String = "Food & Dining",
    val limitAmountInput: String = "150000",
    val selectedCycle: BudgetCycle = BudgetCycle.WEEKLY,
    val spentAmount: Double = 0.0,
    val isEditMode: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false
) {
    val parsedLimitAmount: Double
        get() = limitAmountInput.toDoubleOrNull() ?: 0.0
}
