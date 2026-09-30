package com.example.budgetlimittracking.domain.usecase

import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.model.LimitStatus
import com.example.budgetlimittracking.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class GetExpenseHistoryUseCase(
    private val expenseRepository: ExpenseRepository
) {
    operator fun invoke(
        searchQuery: String = "",
        statusFilter: LimitStatus = LimitStatus.ALL
    ): Flow<List<Expense>> {
        return expenseRepository.getExpenses(searchQuery, statusFilter)
    }
}
