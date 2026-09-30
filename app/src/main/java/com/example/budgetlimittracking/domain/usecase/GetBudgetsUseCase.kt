package com.example.budgetlimittracking.domain.usecase

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCycle
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow

class GetBudgetsUseCase(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke(cycleFilter: BudgetCycle = BudgetCycle.ALL): Flow<List<Budget>> {
        return budgetRepository.getBudgets(cycleFilter)
    }
}
