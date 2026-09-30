package com.example.budgetlimittracking.data.model

import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.Expense

data class ExpenseEntity(
    val id: String,
    val title: String,
    val categoryName: String,
    val amount: Double,
    val timestamp: Long,
    val dateString: String,
    val isExceededLimit: Boolean
) {
    fun toDomain(): Expense {
        return Expense(
            id = id,
            title = title,
            category = try {
                BudgetCategory.valueOf(categoryName)
            } catch (e: Exception) {
                BudgetCategory.OTHER
            },
            amount = amount,
            timestamp = timestamp,
            dateString = dateString,
            isExceededLimit = isExceededLimit
        )
    }

    companion object {
        fun fromDomain(expense: Expense): ExpenseEntity {
            return ExpenseEntity(
                id = expense.id,
                title = expense.title,
                categoryName = expense.category.name,
                amount = expense.amount,
                timestamp = expense.timestamp,
                dateString = expense.dateString,
                isExceededLimit = expense.isExceededLimit
            )
        }
    }
}
