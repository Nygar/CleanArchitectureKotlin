package com.nygar.designsystem.components.skeleton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun SkeletonRow(modifier: Modifier = Modifier) {
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = ThemeConfig.theme.spacing.sizeSpacing2,
            ),
        shape = RoundedCornerShape(ThemeConfig.theme.spacing.sizeSpacing8),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(ThemeConfig.theme.spacing.sizeSpacing45)
                        .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing4)
                        .shimmerEffect(),
            )

            Text(
                text = "",
                modifier = Modifier.fillMaxWidth(0.7f).shimmerEffect(),
            )
        }
    }
}
