package com.example.budgetlimittracking.ui.recordexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.budgetlimittracking.domain.model.BudgetCategory
import com.example.budgetlimittracking.ui.theme.Dimens
import com.example.budgetlimittracking.ui.theme.NumericData
import com.example.budgetlimittracking.ui.theme.ProgressWarning
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecordExpenseScreen(
    uiState: RecordExpenseUiState,
    onTitleChanged: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
    onCategorySelected: (BudgetCategory) -> Unit,
    onSubmitExpense: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onNavigateBack()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Record Expense",
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
                .padding(Dimens.Margin)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpaceLg)
        ) {
            OutlinedTextField(
                value = uiState.title,
                onValueChange = onTitleChanged,
                label = { Text("Expense Title / Note (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            OutlinedTextField(
                value = uiState.amountInput,
                onValueChange = onAmountChanged,
                label = { Text("Expense Amount ($)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                isError = uiState.errorMessage != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Column {
                Text(
                    text = "Budget Category",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(Dimens.SpaceSm))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.SpaceSm),
                    verticalArrangement = Arrangement.spacedBy(Dimens.SpaceSm)
                ) {
                    BudgetCategory.entries.forEach { category ->
                        val isSelected = category == uiState.selectedCategory
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(9999.dp))
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .clickable { onCategorySelected(category) }
                                .padding(horizontal = Dimens.SpaceMd, vertical = Dimens.SpaceSm),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = category.displayName,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                                else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (uiState.isExceededWarning) ProgressWarning else MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (uiState.isExceededWarning)
                        MaterialTheme.colorScheme.errorContainer
                    else
                        MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(Dimens.SpaceMd)
                ) {
                    Text(
                        text = "Budget Impact Calculation",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (uiState.isExceededWarning)
                            MaterialTheme.colorScheme.onErrorContainer
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(Dimens.SpaceSm))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Current Spent:",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = String.format(Locale.US, "$%.2f", uiState.currentSpent),
                            style = NumericData
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.SpaceXs))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Projected Spent:",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = String.format(Locale.US, "$%.2f", uiState.projectedSpent),
                            style = NumericData,
                            color = if (uiState.isExceededWarning) ProgressWarning else MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.SpaceXs))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Category Limit:",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = String.format(Locale.US, "$%.2f", uiState.currentBudgetLimit),
                            style = NumericData
                        )
                    }

                    if (uiState.isExceededWarning) {
                        Spacer(modifier = Modifier.height(Dimens.SpaceSm))
                        Text(
                            text = "Warning: Submitting this expense will exceed your budget limit for ${uiState.selectedCategory.displayName}!",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }

            Button(
                onClick = onSubmitExpense,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.ButtonHeight),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Submit Expense",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
