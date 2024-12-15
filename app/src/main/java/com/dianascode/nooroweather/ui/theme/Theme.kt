package com.dianascode.nooroweather.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.dianascode.nooroweather.R

private val DarkColorScheme = darkColorScheme(
    primary = Gray, // Gray for primary text/icons in dark mode
    secondary = Black, // Black for secondary elements in dark mode
    tertiary = Gray, // Consistency
    background = Black, // Dark background
    surface = Gray, // Components like search bars in dark mode
    onPrimary = Black, // Text/icons on gray (inverted for dark theme)
    onSecondary = Color.White, // Text/icons on black surfaces
    onTertiary = Color.White, // Text/icons on gray surfaces
    onBackground = Gray, // Text on black background
    onSurface = Color.White // Text on gray surfaces
)

private val LightColorScheme = lightColorScheme(
    primary = Black, // Set to black for primary text or icons
    secondary = Gray, // Use gray for components like the search bar
    tertiary = Gray,  // Optional: keep it similar for consistency
    background = Color.White, // White background
    surface = Gray, // For surfaces like cards or elevated components
    onPrimary = Color.White, // Text/icon color on primary
    onSecondary = Black, // Text/icon color on secondary (gray backgrounds)
    onTertiary = Black, // Text/icon color on tertiary
    onBackground = Black, // Text color on white background
    onSurface = Black, // Text color on gray surfaces
)

@Composable
fun NooroWeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}