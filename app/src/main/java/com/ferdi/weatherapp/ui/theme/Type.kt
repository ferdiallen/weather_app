package com.ferdi.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ferdi.weatherapp.core.poppins

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp, color = Color.White
    ),
    bodyMedium = TextStyle(fontFamily = poppins, color = Color.White),
    bodySmall = TextStyle(fontFamily = poppins, color = Color.White),
    displaySmall = TextStyle(fontFamily = poppins, color = Color.White),
    displayMedium = TextStyle(fontFamily = poppins, color = Color.White),
    displayLarge = TextStyle(fontFamily = poppins, color = Color.White),
    headlineSmall = TextStyle(fontFamily = poppins, color = Color.White),
    headlineMedium = TextStyle(fontFamily = poppins, color = Color.White),
    headlineLarge = TextStyle(fontFamily = poppins, color = Color.White),
    titleSmall = TextStyle(fontFamily = poppins, color = Color.White),
    titleMedium = TextStyle(fontFamily = poppins, color = Color.White),
    titleLarge = TextStyle(fontFamily = poppins, color = Color.White),
    labelSmall = TextStyle(fontFamily = poppins, color = Color.White),
    labelMedium = TextStyle(fontFamily = poppins, color = Color.White),
    labelLarge = TextStyle(fontFamily = poppins, color = Color.White)
)