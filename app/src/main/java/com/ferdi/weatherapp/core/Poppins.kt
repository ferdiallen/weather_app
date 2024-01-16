package com.ferdi.weatherapp.core

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ferdi.weatherapp.R

val poppins = FontFamily(
    listOf(
        Font(
            R.font.poppins_light, weight = FontWeight.Light
        ),
        Font(
            R.font.poppins_semibold, weight = FontWeight.SemiBold
        ),
        Font(
            R.font.poppins_regular, weight = FontWeight.Normal
        ),
        Font(
            R.font.poppins_bold, weight = FontWeight.Bold
        ),
    )
)