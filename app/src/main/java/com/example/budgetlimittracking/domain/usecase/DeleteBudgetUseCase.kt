package com.example.budgetlimittracking.domain.usecase

import com.example.budgetlimittracking.domain.repository.BudgetRepository
import javax.inject.Inject

class DeleteBudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) {
    suspend operator fun invoke(budgetId: String) {
        budgetRepository.deleteBudget(budgetId)
    }
}
