package com.example.budgetlimittracking.domain.usecase

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import javax.inject.Inject

class AddOrUpdateBudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) {
    suspend operator fun invoke(budget: Budget) {
        budgetRepository.addOrUpdateBudget(budget)
    }
}
