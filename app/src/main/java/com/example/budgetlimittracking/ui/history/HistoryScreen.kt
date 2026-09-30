package com.example.budgetlimittracking.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.budgetlimittracking.domain.model.Expense
import com.example.budgetlimittracking.domain.model.LimitStatus
import com.example.budgetlimittracking.ui.components.StatusBadge
import com.example.budgetlimittracking.ui.theme.Dimens
import com.example.budgetlimittracking.ui.theme.NumericData
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    uiState: HistoryUiState,
    onSearchQueryChanged: (String) -> Unit,
    onStatusFilterSelected: (LimitStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Submission History",
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(Dimens.Margin),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpaceMd)
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChanged,
                placeholder = { Text("Search by name or category...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpaceSm)
            ) {
                val availableFilters = listOf(
                    LimitStatus.ALL,
                    LimitStatus.EXCEEDED_LIMIT,
                    LimitStatus.WITHIN_BUDGET
                )
                items(availableFilters) { status ->
                    val isSelected = status == uiState.selectedStatusFilter
                    Box(
                        modifier = Modifier
                            .height(Dimens.SegmentHeight)
                            .clip(RoundedCornerShape(9999.dp))
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .clickable { onStatusFilterSelected(status) }
                            .padding(horizontal = Dimens.SpaceMd),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (status) {
                                LimitStatus.ALL -> "All"
                                LimitStatus.EXCEEDED_LIMIT -> "Exceeded Limit"
                                LimitStatus.WITHIN_BUDGET -> "Within Limit"
                                LimitStatus.NEAR_LIMIT -> "Near Limit"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (uiState.expenses.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No submission records found.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = Dimens.SpaceSm),
                    verticalArrangement = Arrangement.spacedBy(Dimens.SpaceSm)
                ) {
                    items(
                        items = uiState.expenses,
                        key = { it.id }
                    ) { expense ->
                        ExpenseHistoryItem(expense = expense)
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseHistoryItem(
    expense: Expense,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SpaceMd),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = expense.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${expense.category.displayName} • ${expense.dateString}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = String.format(Locale.US, "$%.2f", expense.amount),
                    style = NumericData,
                    color = if (expense.isExceededLimit) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                StatusBadge(
                    status = if (expense.isExceededLimit) LimitStatus.EXCEEDED_LIMIT else LimitStatus.WITHIN_BUDGET
                )
            }
        }
    }
}
