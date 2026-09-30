package com.example.budgetlimittracking.domain.repository

import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.model.LimitStatus
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses(
        query: String = "",
        statusFilter: LimitStatus = LimitStatus.ALL
    ): Flow<List<Expense>>

    suspend fun recordExpense(expense: Expense)
}
