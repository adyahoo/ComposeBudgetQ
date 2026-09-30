package com.example.budgetlimittracking.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val ShapeSm = RoundedCornerShape(4.dp)
val ShapeDefault = RoundedCornerShape(8.dp)
val ShapeMd = RoundedCornerShape(12.dp)
val ShapeLg = RoundedCornerShape(16.dp)
val ShapeXl = RoundedCornerShape(24.dp)
val ShapeFull = CircleShape

val Shapes = Shapes(
    small = ShapeSm,
    medium = ShapeMd,
    large = ShapeLg,
    extraLarge = ShapeXl
)
