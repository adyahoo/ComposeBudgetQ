package com.example.budgetlimittracking.data.datasource

import com.example.budgetlimittracking.data.model.BudgetEntity
import com.example.budgetlimittracking.data.model.ExpenseEntity
import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.BudgetCycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor() {
    private val _budgets = MutableStateFlow<List<BudgetEntity>>(initialBudgets())
    val budgets: StateFlow<List<BudgetEntity>> = _budgets.asStateFlow()

    private val _expenses = MutableStateFlow<List<ExpenseEntity>>(initialExpenses())
    val expenses: StateFlow<List<ExpenseEntity>> = _expenses.asStateFlow()

    fun updateBudgetSpent(budgetId: String, newSpentAmount: Double) {
        _budgets.update { list ->
            list.map { budget ->
                if (budget.id == budgetId) budget.copy(spentAmount = newSpentAmount) else budget
            }
        }
    }

    fun upsertBudget(budget: BudgetEntity) {
        _budgets.update { list ->
            val index = list.indexOfFirst { it.id == budget.id }
            if (index >= 0) {
                list.toMutableList().apply { set(index, budget) }
            } else {
                list + budget
            }
        }
    }

    fun addExpense(expense: ExpenseEntity) {
        _expenses.update { list ->
            listOf(expense) + list
        }
    }

    private companion object {
        fun initialBudgets() = listOf(
            BudgetEntity(
                id = "b1",
                categoryName = BudgetCategory.FOOD_DINING.name,
                cycleName = BudgetCycle.DAILY.name,
                limitAmount = 5000000.0,
                spentAmount = 3800000.0
            ),
            BudgetEntity(
                id = "b2",
                categoryName = BudgetCategory.TRANSPORTATION.name,
                cycleName = BudgetCycle.WEEKLY.name,
                limitAmount = 12000000.0,
                spentAmount = 11500000.0
            ),
            BudgetEntity(
                id = "b3",
                categoryName = BudgetCategory.SHOPPING.name,
                cycleName = BudgetCycle.MONTHLY.name,
                limitAmount = 3000000.0,
                spentAmount = 3200000.0
            ),
            BudgetEntity(
                id = "b4",
                categoryName = BudgetCategory.ENTERTAINMENT.name,
                cycleName = BudgetCycle.MONTHLY.name,
                limitAmount = 150000.0,
                spentAmount = 45000.0
            )
        )

        fun initialExpenses() = listOf(
            ExpenseEntity(
                id = "e1",
                categoryName = BudgetCategory.SHOPPING.name,
                amount = 120000.0,
                timestamp = System.currentTimeMillis() - 3600000,
                dateString = "Oct 24, 2023 - 14:30",
                isExceededLimit = true
            ),
            ExpenseEntity(
                id = "e2",
                categoryName = BudgetCategory.TRANSPORTATION.name,
                amount = 25000.0,
                timestamp = System.currentTimeMillis() - 7200000,
                dateString = "Oct 24, 2023 - 12:15",
                isExceededLimit = false
            ),
            ExpenseEntity(
                id = "e3",
                categoryName = BudgetCategory.FOOD_DINING.name,
                amount = 18000.0,
                timestamp = System.currentTimeMillis() - 86400000,
                dateString = "Oct 23, 2023 - 13:00",
                isExceededLimit = false
            ),
            ExpenseEntity(
                id = "e4",
                categoryName = BudgetCategory.SHOPPING.name,
                amount = 120000.0,
                timestamp = System.currentTimeMillis() - 3600000,
                dateString = "Oct 24, 2023 - 14:30",
                isExceededLimit = true
            ),
            ExpenseEntity(
                id = "e5",
                categoryName = BudgetCategory.SHOPPING.name,
                amount = 120000.0,
                timestamp = System.currentTimeMillis() - 3600000,
                dateString = "Oct 24, 2023 - 14:30",
                isExceededLimit = true
            ),
        )
    }
}
