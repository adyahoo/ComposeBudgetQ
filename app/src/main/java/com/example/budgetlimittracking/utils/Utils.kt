package com.example.budgetlimittracking.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.budgetlimittracking.domain.model.BudgetCategory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {
    fun getTodayFormatted(
        pattern: String = "d MMM yyyy",
        locale: Locale = Locale.getDefault()
    ): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)

        return today.format(formatter)
    }

    fun getCategoryIcon(category: BudgetCategory): ImageVector = when (category) {
        BudgetCategory.FOOD_DINING -> Icons.Default.Restaurant
        BudgetCategory.TRANSPORTATION -> Icons.Default.DirectionsBus
        BudgetCategory.HOUSING -> Icons.Default.Home
        BudgetCategory.ENTERTAINMENT -> Icons.Default.ConfirmationNumber
        BudgetCategory.SHOPPING -> Icons.Default.ShoppingCart
        BudgetCategory.UTILITIES -> Icons.Default.Bolt
        BudgetCategory.HEALTH -> Icons.Default.FitnessCenter
        BudgetCategory.OTHER -> Icons.Default.Category
    }
}