package com.example.loveengine.pieces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loveengine.R
import com.example.loveengine.data.loveProgressBarSizes
import com.example.loveengine.pieces.core.LoveBox






@Composable
fun LoveProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    LoveBox(
        modifier = modifier
        ,
        borderSize = loveProgressBarSizes["borderSize"]!!,
        shape = RoundedCornerShape(40)
    ) {
        Box(
            modifier = Modifier
                .padding(loveProgressBarSizes["barPadding"]!!)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(40)
                )
                .fillMaxWidth(progress)
                .fillMaxHeight()

        )
    }
}

@Composable
@Preview(widthDp = 1920, heightDp = 1080)
private fun LoveProgressBarPreview() {

    Image(painter = painterResource(id = R.drawable.background_classroom_1), contentDescription = null)

    Box(modifier = Modifier.fillMaxSize()) {
        LoveProgressBar(
            progress = .5F,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(0.5F)
                .fillMaxHeight(0.07F)
        )
    }
}