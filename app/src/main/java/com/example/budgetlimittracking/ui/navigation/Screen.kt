package com.example.budgetlimittracking.ui.navigation

enum class Screen(val route: String, val title: String) {
    DASHBOARD("dashboard", "Dashboard"),
    ADD_EDIT_BUDGET("add_edit_budget", "Set Budget Limit"),
    RECORD_EXPENSE("record_expense", "Record Expense"),
    HISTORY("history", "History")
}
