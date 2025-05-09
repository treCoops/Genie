package com.advagroup.genie.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.advagroup.genie.R

val QuickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semi_bold, FontWeight.SemiBold),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)

val SFPro = FontFamily(
    Font(R.font.sf_pro_black, FontWeight.Black),
    Font(R.font.sf_pro_bold, FontWeight.Bold),
    Font(R.font.sf_pro_heavy, FontWeight.ExtraBold),
    Font(R.font.sf_pro_light, FontWeight.Light),
    Font(R.font.sf_pro_medium, FontWeight.Medium),
    Font(R.font.sf_pro_regular, FontWeight.Normal),
    Font(R.font.sf_pro_semi_bold, FontWeight.SemiBold),
    Font(R.font.sf_pro_thin, FontWeight.Thin),
    Font(R.font.sf_pro_ultra_light, FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)