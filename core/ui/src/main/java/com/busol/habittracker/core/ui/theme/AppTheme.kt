package com.busol.habittracker.core.ui.theme

import android.graphics.fonts.Font
import android.provider.CalendarContract
import androidx.annotation.Dimension
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object AppTheme {

    val colors: Colors
        @Composable get() = LocalColors.current

    val shapes: Shapes
        @Composable get() = LocalShapes.current

    val fonts: Fonts
        @Composable get() = LocalFonts.current

    val dimensions: Dimensions
        @Composable get() = LocalDimensions.current
}


val LocalShapes = staticCompositionLocalOf { Shapes() }

data class Shapes(
    val card: Shape = RoundedCornerShape(12.0.dp),
    val button: Shape = RoundedCornerShape(16.dp),
    val chip: Shape = RoundedCornerShape(8.0.dp),
    val small: Shape = RoundedCornerShape(4.dp)
)

val LocalColors = staticCompositionLocalOf { Colors() }

data class Colors(
    val overlay: Color = Color(0x4D000000),
    val background: Color = Color(0xFFF0F0F0),
    val divider: Color = Color(0xFF000000),
    val containerColor: Color = Color(0xFFFFFFFF),
    val contentColor: Color = Color(0xFF393637),
    val containerDisabled: Color = Color(0xFFAAAAAA),
    val accent: Color = Color(0xFF7C00FE),
    val error: Color = Color(0xFFF5004F),
    val alertColor: Color = Color(0xFFC0EBA6),
    val placeholderColor: Color = Color(0xFFB0B0B0),
    val borderColors: Color = Color(0xFF55679C)
) {
    val buttonColors: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(
            contentColor = AppTheme.colors.background,
            disabledContentColor = AppTheme.colors.contentColor
        )
}

val LocalDimensions = staticCompositionLocalOf { Dimensions() }

data class Dimensions(
    val paddingDefault: Dp = 16.dp,
    val paddingSmall: Dp = 12.dp,
    val paddingExtraSmall: Dp = 4.dp,
    val spacingExtraLarge: Dp = 16.dp,
    val spacingLarge: Dp = 8.dp,
    val spacingDefault: Dp = 6.dp,
    val spacingSmall: Dp = 4.dp,
    val elevationDefault: Dp = 4.dp,
    val strokeDefault: Dp = 2.dp,
    val strokeLarge: Dp = 4.dp,
    val iconSizeDefault: Dp = 24.dp,
    val iconSizeLarge: Dp = 48.dp,
    val bottomBarSize: Dp = 64.dp,
    val bottomSheetMinHeight: Dp = 100.dp,
    val bottomSheetBaxWidth: Dp = 640.dp
)

val LocalFonts = staticCompositionLocalOf { Fonts() }

data class Fonts(
    val h1: TextStyle = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal
    ),
    val h2: TextStyle = TextStyle(
        fontSize = 25.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal
    ),
    val h3: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal
    ),
    val label: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal
    ),
    val labelSmall: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal
    ),
    val textFieldTitle: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal
    )
)