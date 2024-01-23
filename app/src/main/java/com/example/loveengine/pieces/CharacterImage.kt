package com.example.loveengine.pieces

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CharacterImage(
    modifier: Modifier = Modifier, painter: Painter
) {
    //aligned image for character sprites
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        alignment = Alignment.BottomCenter
    )
}