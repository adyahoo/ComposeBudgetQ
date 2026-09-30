package com.example.budgetlimittracking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.budgetlimittracking.ui.theme.Dimens
import com.example.budgetlimittracking.ui.theme.ProgressCaution
import com.example.budgetlimittracking.ui.theme.ProgressSafe
import com.example.budgetlimittracking.ui.theme.ProgressTrackBase
import com.example.budgetlimittracking.ui.theme.ProgressWarning

@Composable
fun BudgetProgressBar(
    usagePercentage: Float,
    modifier: Modifier = Modifier
) {
    val progressFraction = (usagePercentage / 100f).coerceIn(0f, 1f)

    val fillColor = when {
        usagePercentage >= 96f -> ProgressWarning
        usagePercentage >= 76f -> ProgressCaution
        else -> ProgressSafe
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.ProgressBarHeight)
            .clip(CircleShape)
            .background(ProgressTrackBase)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = progressFraction)
                .clip(CircleShape)
                .background(fillColor)
        )
    }
}
