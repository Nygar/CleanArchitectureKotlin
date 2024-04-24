package com.nygar.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun LottieLoadingView(
    modifier: Modifier = Modifier,
    onAnimationComplete: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.nygar.designsystem.R.raw.login_loading_animation))
    val progress by animateLottieCompositionAsState(
        composition,
        clipSpec = LottieClipSpec.Progress(0f, 1f),
    )
    ConstraintLayout(
        modifier = modifier.fillMaxSize().background(ThemeConfig.theme.color.colorWhite),
    ) {
        val (vAnimation) = createRefs()

        LottieAnimation(
            modifier =
                Modifier.constrainAs(vAnimation) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            composition = composition,
            progress = { progress },
        )
    }

    if (progress == 1f) {
        onAnimationComplete.invoke()
    }
}
