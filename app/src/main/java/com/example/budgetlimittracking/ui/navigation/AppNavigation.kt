package com.example.budgetlimittracking.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import com.example.budgetlimittracking.data.datasource.LocalDataSource
import com.example.budgetlimittracking.data.repository.BudgetRepositoryImpl
import com.example.budgetlimittracking.data.repository.ExpenseRepositoryImpl
import com.example.budgetlimittracking.domain.usecase.GetBudgetsUseCase
import com.example.budgetlimittracking.domain.usecase.GetExpenseHistoryUseCase
import com.example.budgetlimittracking.domain.usecase.RecordExpenseUseCase
import com.example.budgetlimittracking.ui.dashboard.DashboardScreen
import com.example.budgetlimittracking.ui.dashboard.DashboardViewModel
import com.example.budgetlimittracking.ui.history.HistoryScreen
import com.example.budgetlimittracking.ui.history.HistoryViewModel
import com.example.budgetlimittracking.ui.recordexpense.RecordExpenseScreen
import com.example.budgetlimittracking.ui.recordexpense.RecordExpenseViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    // Shared Data Sources & Repositories for initial architecture container
    val localDataSource = remember { LocalDataSource() }
    val budgetRepository = remember { BudgetRepositoryImpl(localDataSource) }
    val expenseRepository = remember { ExpenseRepositoryImpl(localDataSource) }

    // UseCases
    val getBudgetsUseCase = remember { GetBudgetsUseCase(budgetRepository) }
    val recordExpenseUseCase = remember { RecordExpenseUseCase(budgetRepository, expenseRepository) }
    val getExpenseHistoryUseCase = remember { GetExpenseHistoryUseCase(expenseRepository) }

    // ViewModels
    val dashboardViewModel = remember { DashboardViewModel(getBudgetsUseCase) }
    val recordExpenseViewModel = remember { RecordExpenseViewModel(getBudgetsUseCase, recordExpenseUseCase) }
    val historyViewModel = remember { HistoryViewModel(getExpenseHistoryUseCase) }

    var currentScreen by rememberSaveable { mutableStateOf(Screen.DASHBOARD) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Screen.entries.forEach { screen ->
                    NavigationBarItem(
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        label = { Text(screen.title) },
                        icon = {
                            Text(
                                text = when (screen) {
                                    Screen.DASHBOARD -> "📊"
                                    Screen.RECORD_EXPENSE -> "➕"
                                    Screen.HISTORY -> "📜"
                                }
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        val paddingModifier = Modifier.padding(innerPadding)
        when (currentScreen) {
            Screen.DASHBOARD -> {
                val uiState by dashboardViewModel.uiState.collectAsState()
                DashboardScreen(
                    uiState = uiState,
                    onCycleSelected = dashboardViewModel::onCycleSelected,
                    onNavigateToAddBudget = { currentScreen = Screen.RECORD_EXPENSE },
                    modifier = paddingModifier
                )
            }
            Screen.RECORD_EXPENSE -> {
                val uiState by recordExpenseViewModel.uiState.collectAsState()
                RecordExpenseScreen(
                    uiState = uiState,
                    onTitleChanged = recordExpenseViewModel::onTitleChanged,
                    onAmountChanged = recordExpenseViewModel::onAmountChanged,
                    onCategorySelected = recordExpenseViewModel::onCategorySelected,
                    onSubmitExpense = recordExpenseViewModel::submitExpense,
                    onNavigateBack = { currentScreen = Screen.DASHBOARD },
                    modifier = paddingModifier
                )
            }
            Screen.HISTORY -> {
                val uiState by historyViewModel.uiState.collectAsState()
                HistoryScreen(
                    uiState = uiState,
                    onSearchQueryChanged = historyViewModel::onSearchQueryChanged,
                    onStatusFilterSelected = historyViewModel::onStatusFilterSelected,
                    modifier = paddingModifier
                )
            }
        }
    }
}
