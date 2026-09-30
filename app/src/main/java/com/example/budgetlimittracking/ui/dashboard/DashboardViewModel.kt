package com.example.budgetlimittracking.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetlimittracking.domain.model.BudgetCycle
import com.example.budgetlimittracking.domain.usecase.GetBudgetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getBudgetsUseCase: GetBudgetsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadBudgets(BudgetCycle.ALL)
    }

    fun onCycleSelected(cycle: BudgetCycle) {
        _uiState.update { it.copy(selectedCycle = cycle) }
        loadBudgets(cycle)
    }

    private fun loadBudgets(cycle: BudgetCycle) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getBudgetsUseCase(cycle).collect { budgetList ->
                val totalLimit = budgetList.sumOf { it.limitAmount }
                val totalSpent = budgetList.sumOf { it.spentAmount }
                _uiState.update {
                    it.copy(
                        budgets = budgetList,
                        totalLimit = totalLimit,
                        totalSpent = totalSpent,
                        isLoading = false
                    )
                }
            }
        }
    }
}
