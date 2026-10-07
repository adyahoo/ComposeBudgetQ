package com.example.budgetlimittracking.ui.addeditbudget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetlimittracking.domain.model.Budget
import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.domain.model.BudgetCycle
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import com.example.budgetlimittracking.domain.usecase.AddOrUpdateBudgetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddEditBudgetViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository,
    private val addOrUpdateBudgetUseCase: AddOrUpdateBudgetUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditBudgetUiState())
    val uiState: StateFlow<AddEditBudgetUiState> = _uiState.asStateFlow()

    fun loadBudgetForEdit(budgetId: String?) {
        if (budgetId == null) {
            _uiState.update {
                AddEditBudgetUiState(
                    budgetId = null,
                    isEditMode = false
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val budget = budgetRepository.getBudgetById(budgetId).firstOrNull()
            if (budget != null) {
                _uiState.update {
                    it.copy(
                        budgetId = budget.id,
                        category = budget.category,
                        customCategoryName = budget.category.displayName,
                        limitAmountInput = if (budget.limitAmount % 1.0 == 0.0)
                            budget.limitAmount.toLong().toString()
                        else
                            budget.limitAmount.toString(),
                        selectedCycle = budget.cycle,
                        spentAmount = budget.spentAmount,
                        isEditMode = true,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false, isEditMode = false) }
            }
        }
    }

    fun onCategorySelected(category: BudgetCategory) {
        _uiState.update {
            it.copy(
                category = category,
                customCategoryName = category.displayName
            )
        }
    }

    fun onCustomCategoryNameChanged(name: String) {
        _uiState.update { it.copy(customCategoryName = name) }
    }

    fun onLimitAmountChanged(amountInput: String) {
        _uiState.update { it.copy(limitAmountInput = amountInput, errorMessage = null) }
    }

    fun onPresetSelected(presetAmount: Double) {
        val amountStr = if (presetAmount % 1.0 == 0.0) presetAmount.toLong().toString() else presetAmount.toString()
        _uiState.update { it.copy(limitAmountInput = amountStr, errorMessage = null) }
    }

    fun onCycleSelected(cycle: BudgetCycle) {
        if (cycle == BudgetCycle.ALL) return
        _uiState.update { it.copy(selectedCycle = cycle) }
    }

    fun saveBudget() {
        val state = _uiState.value
        val limit = state.limitAmountInput.toDoubleOrNull()
        if (limit == null || limit <= 0) {
            _uiState.update { it.copy(errorMessage = "Please enter a valid budget limit amount.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val budget = Budget(
                id = state.budgetId ?: UUID.randomUUID().toString(),
                category = state.category,
                cycle = state.selectedCycle,
                limitAmount = limit,
                spentAmount = state.spentAmount
            )

            addOrUpdateBudgetUseCase(budget)
            _uiState.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}
