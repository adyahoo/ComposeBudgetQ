package com.example.budgetlimittracking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.budgetlimittracking.domain.model.BudgetCycle
import com.example.budgetlimittracking.ui.theme.Dimens
import com.example.budgetlimittracking.ui.theme.TagDailyBg
import com.example.budgetlimittracking.ui.theme.TagDailyText
import com.example.budgetlimittracking.ui.theme.TagMonthlyBg
import com.example.budgetlimittracking.ui.theme.TagMonthlyText
import com.example.budgetlimittracking.ui.theme.TagWeeklyBg
import com.example.budgetlimittracking.ui.theme.TagWeeklyText

@Composable
fun CycleTag(
    cycle: BudgetCycle,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor) = when (cycle) {
        BudgetCycle.DAILY -> TagDailyBg to TagDailyText
        BudgetCycle.WEEKLY -> TagWeeklyBg to TagWeeklyText
        BudgetCycle.MONTHLY -> TagMonthlyBg to TagMonthlyText
        BudgetCycle.ALL -> MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = modifier
            .height(Dimens.BadgeHeight)
            .clip(RoundedCornerShape(9999.dp))
            .background(bgColor)
            .padding(horizontal = Dimens.SpaceSm, vertical = Dimens.SpaceXs),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cycle.label,
            style = MaterialTheme.typography.labelMedium,
            color = textColor
        )
    }
}
