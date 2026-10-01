package com.example.budgetlimittracking.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.budgetlimittracking.ui.theme.Dimens

@Composable
fun InlineIconText(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = Color.Unspecified,
    iconTint: Color = if (textColor != Color.Unspecified) textColor else LocalContentColor.current,
    iconSize: Dp = 16.dp,
    spacing: Dp = Dimens.SpaceXs,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    iconContentDescription: String? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
            tint = iconTint,
            modifier = Modifier.size(iconSize)
        )
        Spacer(modifier = Modifier.width(spacing))
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            maxLines = maxLines,
            overflow = overflow,
            modifier = Modifier.weight(1f, fill = false)
        )
    }
}
