package com.example.budgetlimittracking.data.repository

import com.example.budgetlimittracking.data.datasource.LocalDataSource
import com.example.budgetlimittracking.data.model.BudgetEntity
import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCycle
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : BudgetRepository {

    override fun getBudgets(cycleFilter: BudgetCycle): Flow<List<Budget>> {
        return localDataSource.budgets.map { list ->
            val domainList = list.map { it.toDomain() }
            if (cycleFilter == BudgetCycle.ALL) {
                domainList
            } else {
                domainList.filter { it.cycle == cycleFilter }
            }
        }
    }

    override fun getBudgetById(budgetId: String): Flow<Budget?> {
        return localDataSource.budgets.map { list ->
            list.find { it.id == budgetId }?.toDomain()
        }
    }

    override suspend fun addOrUpdateBudget(budget: Budget) {
        localDataSource.upsertBudget(BudgetEntity.fromDomain(budget))
    }

    override suspend fun updateSpentAmount(budgetId: String, newSpentAmount: Double) {
        localDataSource.updateBudgetSpent(budgetId, newSpentAmount)
    }
}
