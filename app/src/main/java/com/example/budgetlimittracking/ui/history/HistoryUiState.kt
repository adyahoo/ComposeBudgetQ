package com.example.budgetlimittracking.ui.history

import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.model.LimitStatus

data class HistoryUiState(
    val searchQuery: String = "",
    val selectedStatusFilter: LimitStatus = LimitStatus.ALL,
    val expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false
) {
    val verifiedCount: Int
        get() = expenses.size

    val exceededCount: Int
        get() = expenses.count { it.isExceededLimit }
}
