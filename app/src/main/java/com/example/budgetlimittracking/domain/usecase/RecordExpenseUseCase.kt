package com.example.budgetlimittracking.domain.usecase

import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import com.example.budgetlimittracking.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class RecordExpenseUseCase(
    private val budgetRepository: BudgetRepository,
    private val expenseRepository: ExpenseRepository
) {
    suspend operator fun invoke(
        title: String,
        amount: Double,
        category: BudgetCategory
    ): Result<Expense> {
        if (amount <= 0) {
            return Result.failure(IllegalArgumentException("Amount must be greater than zero."))
        }

        val budgets = budgetRepository.getBudgets().firstOrNull() ?: emptyList()
        val targetBudget = budgets.find { it.category == category }

        val currentSpent = targetBudget?.spentAmount ?: 0.0
        val limitAmount = targetBudget?.limitAmount ?: 0.0
        val updatedSpent = currentSpent + amount

        val isExceeded = targetBudget != null && updatedSpent > limitAmount

        val dateFormat = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault())
        val now = System.currentTimeMillis()

        val newExpense = Expense(
            id = UUID.randomUUID().toString(),
            title = if (title.isBlank()) category.displayName else title,
            category = category,
            amount = amount,
            timestamp = now,
            dateString = dateFormat.format(Date(now)),
            isExceededLimit = isExceeded
        )

        expenseRepository.recordExpense(newExpense)

        if (targetBudget != null) {
            budgetRepository.updateSpentAmount(targetBudget.id, updatedSpent)
        }

        return Result.success(newExpense)
    }
}
