package com.joyner.marvelapp.ui.characters

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest

@Composable
fun CharacterImage(urlImage: String, size: Dp) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(urlImage)
            .crossfade(true)
            .build(),
        contentDescription = "Image character",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(size)
            .clip(CircleShape)
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}