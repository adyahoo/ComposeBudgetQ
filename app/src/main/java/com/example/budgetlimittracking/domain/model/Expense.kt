package com.example.budgetlimittracking.domain.model

data class Expense(
    val id: String,
    val category: BudgetCategory,
    val amount: Double,
    val timestamp: Long,
    val dateString: String,
    val isExceededLimit: Boolean
)
