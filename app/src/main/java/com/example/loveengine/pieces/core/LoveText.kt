package com.example.loveengine.pieces.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun LoveText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 9.sp,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = fontSize,
        modifier = modifier,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}