package com.example.budgetlimittracking.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val PlusJakartaSans = FontFamily.SansSerif

val DisplayLarge = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp,
    lineHeight = 48.sp,
    letterSpacing = (-0.02).em
)

val DisplayLargeMobile = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = (-0.02).em
)

val HeadlineLarge = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = (-0.015).em
)

val HeadlineMedium = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = (-0.01).em
)

val HeadlineSmall = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = (-0.005).em
)

val TitleMedium = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.em
)

val BodyLarge = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.em
)

val BodyMedium = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.em
)

val BodySmall = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.01.em
)

val NumericMetric = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp,
    lineHeight = 36.sp,
    letterSpacing = (-0.03).em
)

val NumericData = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = (-0.01).em
)

val LabelMedium = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.04.em
)

val LabelSmall = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 10.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.06.em
)

// Material 3 Typography mapping
val Typography = Typography(
    displayLarge = DisplayLarge,
    displayMedium = DisplayLargeMobile,
    headlineLarge = HeadlineLarge,
    headlineMedium = HeadlineMedium,
    headlineSmall = HeadlineSmall,
    titleMedium = TitleMedium,
    bodyLarge = BodyLarge,
    bodyMedium = BodyMedium,
    bodySmall = BodySmall,
    labelMedium = LabelMedium,
    labelSmall = LabelSmall
)
