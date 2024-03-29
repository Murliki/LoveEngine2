package com.example.loveengine.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loveengine.R
import com.example.loveengine.data.largeDimen
import com.example.loveengine.data.largeTextSize
import com.example.loveengine.pieces.LoveProgressBar
import com.example.loveengine.pieces.core.LoveBox
import com.example.loveengine.pieces.core.LoveText
import com.example.loveengine.ui.LoveUiState
import com.example.loveengine.ui.LoveViewModel

@Composable
fun LoadingScreen(loveViewModel: LoveViewModel, loveUiState: LoveUiState) {

    //loads after click "Start game" button on main screen
    
    val loadingAnimationProgress by animateIntAsState(
        targetValue = if (loveUiState.animation) 100 else 0,
        animationSpec = tween(10000),
        label = "loading"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_classroom_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LoveProgressBar(
            progress = loadingAnimationProgress.toFloat() / 100F,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(0.5F)
                .fillMaxHeight(0.07F)
        )

        StartButton(
            onClick = { loveViewModel.goToMainScreen() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(largeDimen)
                .fillMaxWidth(0.5F)
                .fillMaxHeight(0.15F)
        )
    }
}


@Composable
private fun StartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LoveBox(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        LoveText(
            text = "Start the game",
            fontSize = largeTextSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}


