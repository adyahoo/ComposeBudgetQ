package com.example.budgetlimittracking.domain.usecase

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import com.example.budgetlimittracking.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

class RecordExpenseUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val expenseRepository: ExpenseRepository
) {
    suspend operator fun invoke(
        title: String,
        amount: Double,
        budget: Budget,
    ): Result<Expense> {
        if (amount <= 0) {
            return Result.failure(IllegalArgumentException("Amount must be greater than zero."))
        }

        val currentSpent = budget.spentAmount
        val limitAmount = budget.limitAmount
        val updatedSpent = currentSpent + amount

        val isExceeded = updatedSpent > limitAmount

        val dateFormat = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault())
        val now = System.currentTimeMillis()

        val newExpense = Expense(
            id = UUID.randomUUID().toString(),
            title = title.ifBlank { budget.category.displayName },
            category = budget.category,
            amount = amount,
            timestamp = now,
            dateString = dateFormat.format(Date(now)),
            isExceededLimit = isExceeded
        )

        expenseRepository.recordExpense(newExpense)

        budgetRepository.updateSpentAmount(budget.id, updatedSpent)

        return Result.success(newExpense)
    }
}
