package com.example.loveengine.pieces.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.loveengine.data.borderColor
import com.example.loveengine.data.defaultBlack
import com.example.loveengine.data.normalDimen
import com.example.loveengine.data.outlineDimen


@Composable
fun LoveBox(
    modifier: Modifier = Modifier,
    borderSize: Dp = outlineDimen,
    shape: Shape = RoundedCornerShape(normalDimen),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .border(
                border = BorderStroke(
                    borderSize,
                    color = borderColor
                ),
                shape = shape
            )
            .background(color = defaultBlack, shape = shape)
    ) {
        content()
    }
}