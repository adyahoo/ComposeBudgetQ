package com.example.budgetlimittracking.ui.recordexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.usecase.GetBudgetsUseCase
import com.example.budgetlimittracking.domain.usecase.RecordExpenseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecordExpenseViewModel(
    private val getBudgetsUseCase: GetBudgetsUseCase,
    private val recordExpenseUseCase: RecordExpenseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecordExpenseUiState())
    val uiState: StateFlow<RecordExpenseUiState> = _uiState.asStateFlow()

    init {
        loadCategoryBudget(_uiState.value.selectedCategory)
    }

    fun onTitleChanged(newTitle: String) {
        _uiState.update { it.copy(title = newTitle) }
    }

    fun onAmountChanged(newAmountInput: String) {
        _uiState.update { it.copy(amountInput = newAmountInput, errorMessage = null) }
        recalculateProjection()
    }

    fun onCategorySelected(category: BudgetCategory) {
        _uiState.update { it.copy(selectedCategory = category, errorMessage = null) }
        loadCategoryBudget(category)
    }

    private fun loadCategoryBudget(category: BudgetCategory) {
        viewModelScope.launch {
            val budgets = getBudgetsUseCase().firstOrNull() ?: emptyList()
            val targetBudget = budgets.find { it.category == category }
            val limit = targetBudget?.limitAmount ?: 0.0
            val spent = targetBudget?.spentAmount ?: 0.0

            _uiState.update {
                it.copy(
                    currentBudgetLimit = limit,
                    currentSpent = spent
                )
            }
            recalculateProjection()
        }
    }

    private fun recalculateProjection() {
        val amount = _uiState.value.amountInput.toDoubleOrNull() ?: 0.0
        val projected = _uiState.value.currentSpent + amount
        val isExceeded = _uiState.value.currentBudgetLimit > 0 && projected > _uiState.value.currentBudgetLimit

        _uiState.update {
            it.copy(
                projectedSpent = projected,
                isExceededWarning = isExceeded
            )
        }
    }

    fun submitExpense() {
        val state = _uiState.value
        val amount = state.amountInput.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            _uiState.update { it.copy(errorMessage = "Please enter a valid expense amount.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = recordExpenseUseCase(
                title = state.title,
                amount = amount,
                category = state.selectedCategory
            )

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Failed to record expense"
                        )
                    }
                }
            )
        }
    }
}
