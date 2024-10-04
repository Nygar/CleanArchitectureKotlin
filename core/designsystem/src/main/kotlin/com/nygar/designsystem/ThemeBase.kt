package com.nygar.designsystem

import androidx.compose.runtime.Immutable
import com.nygar.designsystem.theme.CustomColor
import com.nygar.designsystem.theme.Font
import com.nygar.designsystem.theme.RoundBorder
import com.nygar.designsystem.theme.Spacing

@Immutable
class ThemeBase {
    val spacing = Spacing()
    val color = CustomColor()
    val font = Font()
    val roundBorder = RoundBorder()
}
