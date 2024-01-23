package com.example.loveengine.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.loveengine.R
import com.example.loveengine.classes.data.SaveData
import com.example.loveengine.data.largeTextSize
import com.example.loveengine.data.normalDimen
import com.example.loveengine.pieces.SettingsDialog
import com.example.loveengine.pieces.core.LoveBox
import com.example.loveengine.pieces.core.LoveText
import com.example.loveengine.ui.LoveUiState
import com.example.loveengine.ui.LoveViewModel

private var settingsChecker = false


@Composable
fun StartScreen(loveViewModel: LoveViewModel, loveUiState: LoveUiState) {

    //screen that launching first

    //Settings properties


    val number = loveUiState.currentSaveData.number
    val id = loveUiState.currentSaveData.id
    val day = loveUiState.currentSaveData.day


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val currentStartScreen = R.drawable.start_screen_background
        var isInSetting by remember { mutableStateOf(false) }



        Image(
            painter = painterResource(id = currentStartScreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (isInSetting) {

            if (!settingsChecker) {
                settingsChecker = true
                loveViewModel.updateUiStateSave()
            }

            SettingsDialog(


                onDismissRequest = {
                    isInSetting = false
                    val saveData = SaveData(
                        id = id,
                        sound = loveUiState.currentSaveData.sound,
                        number = number,
                        day = day,
                        charSpeed = loveUiState.currentSaveData.charSpeed,
                        music = loveUiState.currentSaveData.music
                    )
                    loveViewModel.saveDataToDatabase(data = saveData)
                    settingsChecker = false
                    loveViewModel.updateUiStateSave()
                },
                progressMusic = loveUiState.currentSaveData.music,
                onValueChangeMusic = { loveViewModel.setNewMusicValue(it) },
                progressSound = loveUiState.currentSaveData.sound,
                onValueChangeSound = { loveViewModel.setNewSoundValue(it) },
                progressTextSpeed = loveUiState.currentSaveData.charSpeed.toFloat(),
                onValueChangeTextSpeed = { loveViewModel.setNewCharSpeedValue(it) }
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight(0.45F)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            ) {



                MenuElement(
                    onClick = {
                        loveViewModel.goToLoadingScreen()
                    },
                    text = "Start the journey",
                    modifier = Modifier
                        .fillMaxHeight(0.33F)
                        .fillMaxWidth()
                        .padding(top = normalDimen)
                )


                MenuElement(
                    onClick = { /*TODO go to settings screen*/ },
                    text = "Gallery",
                    modifier = Modifier
                        .fillMaxHeight(0.5F)
                        .fillMaxWidth()
                        .padding(top = normalDimen)
                )

                MenuElement(
                    onClick = { isInSetting = true },
                    text = "Settings",
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = normalDimen)
                )

            }
        }
    }
}

@Composable
private fun MenuElement(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    LoveBox(
        modifier = modifier
            .clickable { onClick() }
    ) {
        LoveText(
            text = text,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = largeTextSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

}