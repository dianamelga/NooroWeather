package com.dianascode.nooroweather.ui.common.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter

val AsyncImagePlaceHolder = BrushPainter(
    Brush.linearGradient(
        listOf(
            Color(color = 0xFFFFFFFF),
            Color(color = 0xFFDDDDDD),
        )
    )
)