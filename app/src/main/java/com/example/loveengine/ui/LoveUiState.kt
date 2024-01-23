package com.example.loveengine.ui

import com.example.loveengine.R
import com.example.loveengine.classes.MenuDialog
import com.example.loveengine.classes.Character
import com.example.loveengine.classes.Screens
import com.example.loveengine.classes.data.SaveData

data class LoveUiState(

    var currentCharacter1: Character? = null,
    var currentCharacter2: Character? = null,
    var currentCharacter3: Character? = null,
    var currentSpeaker: String = "",
    var currentText: String = "",
    var currentBackGround: Int = R.drawable.background_classroom_1,
    var currentDialog: MenuDialog? = null,
    var currentMusic: Int? = null,
    var currentScreen: Screens = Screens.START,
    var currentLoadingProgress: Int = 0,
    var animation: Boolean = false,

    var currentSaveData: SaveData = SaveData()
)