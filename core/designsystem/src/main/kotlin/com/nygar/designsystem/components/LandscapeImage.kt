package com.nygar.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nygar.designsystem.R

@Composable
fun LandscapeImage(
    modifier: Modifier = Modifier,
    image: String = ""
){
    AsyncImage(
        modifier = modifier.fillMaxWidth(),
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_placeholder_background),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
    )
}

@Preview()
@Composable
fun PreviewLandscapeImage(){
    LandscapeImage()
}
