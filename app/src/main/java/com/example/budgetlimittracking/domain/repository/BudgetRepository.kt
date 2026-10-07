package com.example.budgetlimittracking.domain.repository

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCycle
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getBudgets(cycleFilter: BudgetCycle = BudgetCycle.ALL): Flow<List<Budget>>
    fun getBudgetById(budgetId: String): Flow<Budget?>
    suspend fun addOrUpdateBudget(budget: Budget)
    suspend fun updateSpentAmount(budgetId: String, newSpentAmount: Double)
    suspend fun deleteBudget(budgetId: String)
}
