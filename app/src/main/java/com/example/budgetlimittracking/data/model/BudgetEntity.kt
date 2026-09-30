package com.example.budgetlimittracking.data.model

import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.BudgetCycle

data class BudgetEntity(
    val id: String,
    val categoryName: String,
    val cycleName: String,
    val limitAmount: Double,
    val spentAmount: Double
) {
    fun toDomain(): Budget {
        return Budget(
            id = id,
            category = try {
                BudgetCategory.valueOf(categoryName)
            } catch (e: Exception) {
                BudgetCategory.OTHER
            },
            cycle = try {
                BudgetCycle.valueOf(cycleName)
            } catch (e: Exception) {
                BudgetCycle.MONTHLY
            },
            limitAmount = limitAmount,
            spentAmount = spentAmount
        )
    }

    companion object {
        fun fromDomain(budget: Budget): BudgetEntity {
            return BudgetEntity(
                id = budget.id,
                categoryName = budget.category.name,
                cycleName = budget.cycle.name,
                limitAmount = budget.limitAmount,
                spentAmount = budget.spentAmount
            )
        }
    }
}
