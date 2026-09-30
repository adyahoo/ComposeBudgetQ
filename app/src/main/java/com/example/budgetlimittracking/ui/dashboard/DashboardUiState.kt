package com.example.budgetlimittracking.ui.dashboard

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCycle

data class DashboardUiState(
    val selectedCycle: BudgetCycle = BudgetCycle.ALL,
    val budgets: List<Budget> = emptyList(),
    val totalLimit: Double = 0.0,
    val totalSpent: Double = 0.0,
    val isLoading: Boolean = false
)
