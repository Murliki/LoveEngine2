package com.example.loveengine.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loveengine.R
import com.example.loveengine.classes.data.SaveData
import com.example.loveengine.data.highCharacterPadding
import com.example.loveengine.data.lowCharacterPadding
import com.example.loveengine.data.middleCharacterPadding
import com.example.loveengine.data.normalDimen
import com.example.loveengine.data.timingForGirlsAnimation
import com.example.loveengine.pieces.CharacterImage
import com.example.loveengine.pieces.CharacterVisibility
import com.example.loveengine.pieces.ChoiceDialog
import com.example.loveengine.pieces.NovellSettingsButton
import com.example.loveengine.pieces.SettingsDialog
import com.example.loveengine.pieces.TextMain
import com.example.loveengine.pieces.core.LoveBox
import com.example.loveengine.pieces.core.LoveText
import com.example.loveengine.ui.LoveUiState
import com.example.loveengine.ui.LoveViewModel

private var settingsChecker = false

@Composable
fun MainScreen(loveViewModel: LoveViewModel, loveUiState: LoveUiState) {

    val number = loveUiState.currentSaveData.number
    val id = loveUiState.currentSaveData.id
    val day = loveUiState.currentSaveData.day




    Box(
        modifier = Modifier.fillMaxSize()
    ) {



        Image(
            painter = painterResource(loveUiState.currentBackGround),
            contentDescription = null, //TODO
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val firstSpacer by animateIntAsState(
            targetValue = if (loveUiState.currentCharacter3 != null) highCharacterPadding
            else if (loveUiState.currentCharacter2 != null) middleCharacterPadding
            else lowCharacterPadding,
            label = "First Spacer",
            animationSpec = tween(durationMillis = timingForGirlsAnimation)
        )

        val secondSpacer by animateIntAsState(
            targetValue = if (loveUiState.currentCharacter3 != null) highCharacterPadding
            else middleCharacterPadding,
            label = "Second Spacer",
            animationSpec = tween(durationMillis = timingForGirlsAnimation)
        )

        CharacterVisibility(
            visible = loveUiState.currentCharacter1 != null, modifier = Modifier.align(
                Alignment.BottomCenter
            )
        ) {
            Spacer(
                modifier = Modifier.width(
                    firstSpacer.dp
                )
            )
            CharacterImage(
                modifier = Modifier
                    .height(350.dp)
                    .width(128.dp),
                painter = painterResource(id = if (loveUiState.currentCharacter1 != null) loveUiState.currentCharacter1!!.currentEmoji() else R.drawable.null_character)
            )
        }



        CharacterVisibility(
            visible = (loveUiState.currentCharacter2 != null), modifier = Modifier.align(
                Alignment.BottomCenter
            )
        ) {


            CharacterImage(
                modifier = Modifier
                    .height(350.dp)
                    .width(128.dp),
                painter = painterResource(id = if (loveUiState.currentCharacter2 != null)
                    loveUiState.currentCharacter2!!.currentEmoji() else R.drawable.null_character)
            )
            Spacer(
                modifier = Modifier.width(
                    secondSpacer.dp
                )
            )

        }


        CharacterVisibility(
            visible = loveUiState.currentCharacter3 != null, modifier = Modifier.align(
                Alignment.BottomCenter
            )
        ) {

            CharacterImage(
                modifier = Modifier
                    .height(350.dp)
                    .width(128.dp)
                    .align(Alignment.Bottom),
                painter = painterResource(
                    id = if (loveUiState.currentCharacter3 != null)
                        loveUiState.currentCharacter3!!.currentEmoji()
                    else R.drawable.null_character
                )
            )
        }




        loveUiState.currentDialog?.apply {
            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                (loveUiState.currentDialog)?.let { currentDialog ->
                    ChoiceDialog(
                        modifier = Modifier
                            .fillMaxWidth(0.25F)
                            .fillMaxHeight(0.35F),
                        menuDialog = currentDialog,
                        changeBranch = {loveViewModel.pushNewNumber(it)},
                        nextData = {loveViewModel.nextData()}
                    )
                }
                Spacer(modifier = Modifier.padding(32.dp))
            }
        }

        TextMain(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.85F)
                .fillMaxHeight(0.3F)
                .padding(bottom = normalDimen),
            nameOfCharacter = loveUiState.currentSpeaker,
            text = loveUiState.currentText,
            onClick = { loveViewModel.nextData() },
            enabled = loveUiState.currentDialog == null,
            speed = loveUiState.currentSaveData.charSpeed.toLong()
        )

        var isInSettings by remember { mutableStateOf(false) }

        NovellSettingsButton(
            onClick = { isInSettings = true },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomStart)
                .padding(bottom = normalDimen, start = normalDimen)
        )

        var isExiting by remember {
            mutableStateOf(false)
        }

        BackHandler (
            enabled = !isExiting,
            onBack = {
                if(isInSettings) {
                    isExiting = true

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
                } else {
                    isInSettings = true
                }
            }
        )


        AnimatedVisibility(visible = isExiting) {
            LoveBox(
                modifier = Modifier.fillMaxSize(0.8F)
            ) {
                LoveText(
                    text = "Are you sure, that you want to exit?",
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    )
                )
                OutlinedButton(
                    onClick = { isExiting = false },
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    LoveText(text = "No")
                }
                
                OutlinedButton(
                    onClick = { /*TODO finish activity*/ },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    LoveText(text = "Yes")
                }
            }
        }

        AnimatedVisibility(visible = isInSettings) {
            SettingsDialog(
                onDismissRequest = {
                    isInSettings = false
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
                progressSound =loveUiState.currentSaveData.sound,
                onValueChangeSound = { loveViewModel.setNewSoundValue(it) },
                progressTextSpeed = loveUiState.currentSaveData.charSpeed.toFloat(),
                onValueChangeTextSpeed = { loveViewModel.setNewCharSpeedValue(it) }
            )
        }
    }
}

