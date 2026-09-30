package com.example.budgetlimittracking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.budgetlimittracking.domain.model.LimitStatus
import com.example.budgetlimittracking.ui.theme.Dimens
import com.example.budgetlimittracking.ui.theme.ProgressCaution
import com.example.budgetlimittracking.ui.theme.ProgressSafe
import com.example.budgetlimittracking.ui.theme.ProgressWarning
import com.example.budgetlimittracking.ui.theme.SecondaryContainerLight
import com.example.budgetlimittracking.ui.theme.TertiaryContainerLight
import com.example.budgetlimittracking.ui.theme.WarningContainerLight

@Composable
fun StatusBadge(
    status: LimitStatus,
    modifier: Modifier = Modifier
) {
    val (bgColor, dotColor, textColor, text) = when (status) {
        LimitStatus.WITHIN_BUDGET -> Quadruple(
            SecondaryContainerLight,
            ProgressSafe,
            ProgressSafe,
            "Within Budget"
        )
        LimitStatus.NEAR_LIMIT -> Quadruple(
            WarningContainerLight,
            ProgressCaution,
            ProgressCaution,
            "Near Limit"
        )
        LimitStatus.EXCEEDED_LIMIT -> Quadruple(
            TertiaryContainerLight,
            ProgressWarning,
            ProgressWarning,
            "Over Budget"
        )
        LimitStatus.ALL -> Quadruple(
            MaterialTheme.colorScheme.surfaceVariant,
            MaterialTheme.colorScheme.onSurfaceVariant,
            MaterialTheme.colorScheme.onSurfaceVariant,
            "All"
        )
    }

    Row(
        modifier = modifier
            .height(Dimens.BadgeHeight)
            .clip(RoundedCornerShape(9999.dp))
            .background(bgColor)
            .padding(horizontal = Dimens.SpaceSm, vertical = Dimens.SpaceXs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.StatusDotSize)
                .clip(CircleShape)
                .background(dotColor)
        )
        Spacer(modifier = Modifier.width(Dimens.SpaceXs))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

private data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)
