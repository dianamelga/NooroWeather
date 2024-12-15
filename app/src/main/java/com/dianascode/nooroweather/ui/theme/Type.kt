package com.dianascode.nooroweather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dianascode.nooroweather.R

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 105.sp,
        fontSize = 70.sp
    ),
    displayMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 45.sp,
        fontSize = 30.sp
    ),
    displaySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.5.sp,
        fontSize = 15.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 90.sp,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 30.sp,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.5.sp,
        fontSize = 15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.5.sp,
        fontSize = 15.sp
    ),
    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)