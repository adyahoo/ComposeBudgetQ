package com.example.budgetlimittracking.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetlimittracking.ui.dashboard.DashboardScreen
import com.example.budgetlimittracking.ui.dashboard.DashboardViewModel
import com.example.budgetlimittracking.ui.history.HistoryScreen
import com.example.budgetlimittracking.ui.history.HistoryViewModel
import com.example.budgetlimittracking.ui.recordexpense.RecordExpenseScreen
import com.example.budgetlimittracking.ui.recordexpense.RecordExpenseViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    recordExpenseViewModel: RecordExpenseViewModel = hiltViewModel(),
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
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
                            Icon(
                                imageVector = when (screen) {
                                    Screen.DASHBOARD -> Icons.Default.Dashboard
                                    Screen.RECORD_EXPENSE -> Icons.Default.AddCircleOutline
                                    Screen.HISTORY -> Icons.Default.Timelapse
                                },
                                contentDescription = "${screen.title} Icon"
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
                    onAmountChanged = recordExpenseViewModel::onAmountChanged,
                    onBudgetSelected = recordExpenseViewModel::onBudgetSelected,
                    onSubmitExpense = recordExpenseViewModel::submitExpense,
                    onNavigateBack = { currentScreen = Screen.DASHBOARD },
                    modifier = paddingModifier
                )
            }
            Screen.HISTORY -> {
                val uiState by historyViewModel.uiState.collectAsState()
                HistoryScreen(
                    uiState = uiState,
                    onStatusFilterSelected = historyViewModel::onStatusFilterSelected,
                    modifier = paddingModifier
                )
            }
        }
    }
}
