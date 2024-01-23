package com.example.loveengine.pieces

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CharacterVisibility(
    visible: Boolean,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) {
    //aligned and animated visibility for characters
    AnimatedVisibility(
        visible = (visible),
        modifier = modifier
            .fillMaxSize(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            content()
        }
    }
}