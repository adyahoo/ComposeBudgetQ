package com.example.budgetlimittracking.data.repository

import com.example.budgetlimittracking.data.datasource.LocalDataSource
import com.example.budgetlimittracking.data.model.ExpenseEntity
import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.model.LimitStatus
import com.example.budgetlimittracking.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryImpl(
    private val localDataSource: LocalDataSource
) : ExpenseRepository {

    override fun getExpenses(
        query: String,
        statusFilter: LimitStatus
    ): Flow<List<Expense>> {
        return localDataSource.expenses.map { list ->
            list.map { it.toDomain() }
                .filter { expense ->
                    val matchesQuery = query.isBlank() ||
                            expense.title.contains(query, ignoreCase = true) ||
                            expense.category.displayName.contains(query, ignoreCase = true)

                    val matchesStatus = when (statusFilter) {
                        LimitStatus.ALL -> true
                        LimitStatus.EXCEEDED_LIMIT -> expense.isExceededLimit
                        LimitStatus.WITHIN_BUDGET -> !expense.isExceededLimit
                        LimitStatus.NEAR_LIMIT -> true
                    }

                    matchesQuery && matchesStatus
                }
        }
    }

    override suspend fun recordExpense(expense: Expense) {
        localDataSource.addExpense(ExpenseEntity.fromDomain(expense))
    }
}
