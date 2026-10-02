package com.example.budgetlimittracking.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetlimittracking.domain.model.LimitStatus
import com.example.budgetlimittracking.domain.usecase.GetExpenseHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getExpenseHistoryUseCase: GetExpenseHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadHistory("", LimitStatus.ALL)
    }

    fun onStatusFilterSelected(status: LimitStatus) {
        _uiState.update { it.copy(selectedStatusFilter = status) }
        loadHistory(_uiState.value.searchQuery, status)
    }

    private fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        loadHistory(query, _uiState.value.selectedStatusFilter)
    }

    private fun loadHistory(query: String, status: LimitStatus) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getExpenseHistoryUseCase(query, status).collect { expenseList ->
                _uiState.update {
                    it.copy(
                        expenses = expenseList,
                        isLoading = false
                    )
                }
            }
        }
    }
}
