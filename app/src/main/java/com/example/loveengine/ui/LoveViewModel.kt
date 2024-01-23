package com.example.loveengine.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.loveengine.LoveEngine
import com.example.loveengine.R
import com.example.loveengine.classes.Character
import com.example.loveengine.classes.Emotion
import com.example.loveengine.classes.MenuDialog
import com.example.loveengine.classes.Screens
import com.example.loveengine.classes.data.NovellData
import com.example.loveengine.classes.data.SaveData
import com.example.loveengine.classes.data.SavedEnvironment
import com.example.loveengine.data.dao.NovellDataDao
import com.example.loveengine.test.anya
import com.example.loveengine.test.errorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

private const val TAG = "MUR"

class LoveViewModel(private val novellDataDao: NovellDataDao) : ViewModel() {


    //to database
    private var dataNumber = -1
    private var listOfData = flowOf(listOf(errorData))
    private var currentText: String = ""
    private var currentSpeaker: String = ""
    private var currentDialog: MenuDialog? = null
    private var currentLoadingProgress = 0
    private var animation = false

    //cells ->
    private var currentFirstCharacter: Character? = null
    private var currentSecondCharacter: Character? = null
    private var currentThirdCharacter: Character? = null

    private var currentBackground: Int = R.drawable.background_classroom_1
    private var currentMusic: Int? = null

    private val _uiState = MutableStateFlow(LoveUiState())
    val uiState: StateFlow<LoveUiState> = _uiState.asStateFlow()

    private var currentSaveData: SaveData =
        runBlocking { novellDataDao.getSave().first().first() }

    private var currentScreen = Screens.START

    private var environment: SavedEnvironment = runBlocking {
        novellDataDao.getEnvironment().first()
    }

    private fun startAnimation() {
        animation = true
    }

    fun goToLoadingScreen() {
        currentScreen = Screens.LOADING
        updateUiState()
        runBlocking {
            environment = novellDataDao.getEnvironment().first()
        }
        if (environment.id == 0) {
            environment = runBlocking { novellDataDao.getEnvironment().first() }
            Log.e(TAG, "runBlocking error")
        }
        if (environment.id == 0) {
            Log.e(TAG, "Still runBLockingError")
            throw RuntimeException()
        }

        currentSaveData = runBlocking {
            novellDataDao.getSave().first().first()
        }

        listOfData = if (currentSaveData.day == 1) novellDataDao.getAllDataFromTableFirstTable()
        else /*TODO*/ novellDataDao.getAllDataFromTableFirstTable()


        environment.apply {
            currentBackground = background ?: R.drawable.background_classroom_1
            currentMusic = music

            currentFirstCharacter =
                if (character1 != null) fromStringToCharacter(character1) else null

            currentSecondCharacter =
                if (character2 != null) fromStringToCharacter(character2) else null
            currentThirdCharacter =
                if (character3 != null) fromStringToCharacter(character3) else null

            if (currentFirstCharacter != null)
                pushNewEmoji(1, fromStringToEmotion(emotion1))
            if (currentSecondCharacter != null)
                pushNewEmoji(2, fromStringToEmotion(emotion2))
            if (currentThirdCharacter != null)
                pushNewEmoji(3, fromStringToEmotion(emotion3))

            if (currentDialog != null) {
                currentSaveData = currentSaveData.copy(
                    number = currentSaveData.number - 1
                )
            }

            startAnimation()
            updateUiState()

            //TODO currentSetter = setter
        }


        pushNewNumber(currentSaveData.number - 1)
        nextData()

    }


    private fun increaseLoadingProgress() {
        currentLoadingProgress++
        updateUiState()
    }

    fun goToMainScreen() {
        currentScreen = Screens.MAIN
        Log.i(TAG, "currentBackground = $currentBackground")
        Log.i(TAG, "expectedBackground = ${R.drawable.field_background}")
        updateUiState()
    }

    private fun updateUiState() {
        //updating ui state
        _uiState.update { currentState ->
            currentState.copy(
                currentCharacter1 = currentFirstCharacter,
                currentCharacter2 = currentSecondCharacter,
                currentCharacter3 = currentThirdCharacter,
                currentText = currentText,
                currentBackGround = currentBackground,
                currentSpeaker = currentSpeaker,
                currentDialog = currentDialog,
                currentMusic = currentMusic,
                currentScreen = currentScreen,
                currentLoadingProgress = currentLoadingProgress,
                animation = animation,

                currentSaveData = currentSaveData
            )
        }
    }

    fun updateUiStateSave() {
        currentSaveData = runBlocking {
            novellDataDao.getSave().first().first()
        }

        updateUiState()
    }

    fun saveDataToDatabase(data: SaveData) { //TODO from preferences
        currentSaveData = data
        runBlocking { novellDataDao.updateSave(data) }
        updateUiStateSave()
    }


    /*fun loadNewDataset() {

        private val nullEnvironment = SavedEnvironment(
        id = 0, null, null, null, null,
        null, null, null, null, null, null,
    )

        // checks the saved list of data number and set it
        listOfData = novellDataDao.getAllDataFromTableFirstTable()
        pushNewNumber(-1)
        deleteAllCharacters()
        currentMusic = null
        viewModelScope.launch {
            novellDataDao.updateEnvironment(nullEnvironment)
        }


        nextData()
    }*/

    private fun fromStringToEmotion(string: String?): Emotion {
        if (string != null) {
            if (string == "null") {
                Log.i(TAG, "String null emotion")
            }
            return when (string) {
                //database emotion -> real Emotion
                "boring" -> Emotion.BORING
                "scared" -> Emotion.SCARED
                "default" -> Emotion.DEFAULT
                "excited" -> Emotion.EXCITED
                "happy" -> Emotion.HAPPY
                "horny" -> Emotion.HORNY
                "null" -> Emotion.DEFAULT
                else -> {
                    Log.e(
                        TAG, "in LoveViewModel -> fromStringToEmotion -> ERROR:" +
                                "there is no emotion with name $string"
                    )
                    throw RuntimeException()
                }
            }
        }
        return Emotion.DEFAULT
    }

    private fun fromEmotionToString(emotion: Emotion?): String? {
        return if (emotion != null) {
            when (emotion) {
                Emotion.BORING -> "boring"
                Emotion.SCARED -> "scared"
                Emotion.DEFAULT -> "default"
                Emotion.EXCITED -> "excited"
                Emotion.HAPPY -> "happy"
                Emotion.HORNY -> "horny"
            }
        } else {
            Log.i(TAG, "fromStringToEmotion returned null")
            null
        }
    }

    private fun fromStringToCharacter(string: String?): Character {
        val lowerCaseString = string?.lowercase()
        if (lowerCaseString != null) {
            return when (lowerCaseString) {
                "anya" -> anya
                else -> {
                    throw Exception(
                        "in LoveViewModel -> fromStringToCharacter -> " +
                                "ERROR: There is no character with name $lowerCaseString"
                    )
                }
            }
        }
        return anya
    }

    private fun fromIntToDialog(number: Int?): MenuDialog? {
        if (number != null) {
            return when (number) {
                1 -> MenuDialog.test
                else ->
                    throw Exception("wrong MenuDialog number")
            }
        }
        return null
    }

    private fun fromIntToBoolean(number: Int?): Boolean {
        if (number != null) {
            return number > 0
        }
        return false
    }

    private fun pushNewEmoji(
        characterNum: Int = 1,
        emotion: Emotion = Emotion.DEFAULT
    ) {
        /*set new emotion for the existing character
        if characterNum is more than 3 -> firstCharacter will apply new emotion
        if character do not exist -> throw null error
        if there is no such emotion of character -> set default emotion */
        when (characterNum) {
            1 -> currentFirstCharacter
            2 -> currentSecondCharacter
            3 -> currentThirdCharacter
            else -> throw Exception("Unexpected count in pushNewEmoji")
        }?.apply {
            when (emotion) {
                Emotion.DEFAULT -> setDefault()
                Emotion.EXCITED -> setExcited()
                Emotion.BORING -> setBoring()
                Emotion.HAPPY -> setHappy()
                Emotion.SCARED -> setScared()
                Emotion.HORNY -> setHorny()
            }
        }

        val scopeEmotion = fromEmotionToString(emotion)
        environment = environment.copy(
            emotion1 = if (characterNum == 1) scopeEmotion else environment.emotion1,
            emotion2 = if (characterNum == 2) scopeEmotion else environment.emotion2,
            emotion3 = if (characterNum == 3) scopeEmotion else environment.emotion3
        )
        viewModelScope.launch {
            novellDataDao.updateEnvironment(
                environment
            )
        }
    }

    private fun pushNewCharacter(count: Int, character: Character) {
        /* max character count -> 3
        set new character in the count cell after updating ui state
        for example count == 3, character = Anya
        currentThirdCharacter = Anya */

        Log.i(TAG, "New character pushed, count = ${count}, character = ${character.name} ")

        when (count) {
            1 -> {
                currentFirstCharacter = character
            }

            2 -> {
                currentSecondCharacter = character
            }

            3 -> {
                currentThirdCharacter = character
            }

            else -> {
                Log.e(TAG, "Unexpected count = $count in ")
                throw RuntimeException()
            }
        }

        environment = environment.copy(
            character1 = if (count != 1) environment.character1 else fromCharacterToString(
                character
            ),
            character2 = if (count != 2) environment.character2 else fromCharacterToString(
                character
            ),
            character3 = if (count != 3) environment.character3 else fromCharacterToString(
                character
            )
        )

        Log.i(
            TAG,
            "Environment copy:\n" +
                    "character1 = ${environment.character1}\n" +
                    "character2 = ${environment.character2}\n" +
                    "character3 = ${environment.character3}"
        )

        viewModelScope.launch {
            novellDataDao.updateEnvironment(
                environment
            )
        }
    }

    private fun fromDatabaseBackgroundToReal(background: Int): Int{
        return when (background){
            2131034154 -> R.drawable.field_background
            else -> {
                Log.e(TAG, "fromDatabaseBackgroundToReal doesn't work correctly")
                background
            }
        }
    }

    private fun fromCharacterToString(character: Character?): String? {
        return if (character == null)
            null
        else if (character.name == "Anya")
            "Anya"
        else
            throw Exception("There is no such character in fromStringToCharacter")
    }

    private fun pushNewMusic(music: Int?) {
        currentMusic = music
    }

    private fun deleteCharacter(count: Int) {
        //delete character on count cell
        when (count) {
            1 -> {
                currentFirstCharacter = null
            }

            2 -> {
                currentSecondCharacter = null
            }

            3 -> {
                currentThirdCharacter = null
            }

            else -> {
                throw Exception("In deleteCharacter, count isn't correct")
            }
        }

        environment = when (count) {
            1 -> {
                environment.copy(
                    character1 = null
                )
            }

            2 -> {
                environment.copy(
                    character2 = null
                )
            }

            3 -> {
                environment.copy(
                    character3 = null
                )
            }

            else -> throw Exception("Wow, Idk how do u get this error")
        }

        viewModelScope.launch {
            novellDataDao.updateEnvironment(
                environment
            )
        }
    }

    private fun deleteAllCharacters() {
        //delete all characters in the cells
        currentThirdCharacter = null
        currentFirstCharacter = null
        currentSecondCharacter = null
        environment = environment.copy(
            character1 = null,
            character2 = null,
            character3 = null,
        )
        viewModelScope.launch {
            novellDataDao.updateEnvironment(
                environment
            )
        }
    }

    private fun updateFromData(data: NovellData) {
        currentText = data.text ?: ""
        currentSpeaker = data.speaker

        if (data.addCharacter1 != null)
            pushNewCharacter(1, fromStringToCharacter(data.addCharacter1))

        if (data.addCharacter2 != null)
            pushNewCharacter(2, fromStringToCharacter(data.addCharacter2))

        if (data.addCharacter3 != null)
            pushNewCharacter(3, fromStringToCharacter(data.addCharacter3))

        if (fromIntToBoolean(data.deleteAllCharacters))
            deleteAllCharacters()

        if (data.deleteCharacter != null)
            deleteCharacter(data.deleteCharacter)

        if (data.setEmotion1 != null)
            pushNewEmoji(1, fromStringToEmotion(data.setEmotion1))

        if (data.setEmotion2 != null)
            pushNewEmoji(2, fromStringToEmotion(data.setEmotion2))

        if (data.setEmotion3 != null)
            pushNewEmoji(3, fromStringToEmotion(data.setEmotion3))

        if (data.setNewMusic != null)
            pushNewMusic(data.setNewMusic)

        if (data.setNewBackground != null) {
            currentBackground = fromDatabaseBackgroundToReal(data.setNewBackground)
            environment = environment.copy(
                background = currentBackground
            )
            viewModelScope.launch {
                novellDataDao.updateEnvironment(
                    environment
                )
            }
        }

        currentDialog = if (data.dialog != null)
            fromIntToDialog(data.dialog)
        else
            null

        updateUiState()
    }

    fun nextData() {
        if (dataNumber < runBlocking { listOfData.first().size - 1 })
            dataNumber++
        else
            dataNumber = 0

        updateFromData(data = runBlocking { listOfData.first()[dataNumber] })

        currentSaveData = currentSaveData.copy(
            number = dataNumber - 1
        )
        viewModelScope.launch {
            novellDataDao.updateSave(currentSaveData)
        }
    }

    fun pushNewNumber(number: Int) {
        if (number > 0) {
            dataNumber = number
        }
        currentDialog = null

        currentSaveData = currentSaveData.copy(
            id = 1,
            number = number - 1
        )

    }

    fun setNewMusicValue(it: Float) {
        currentSaveData = SaveData(
            id = 1,
            day = currentSaveData.day,
            number = currentSaveData.number,
            charSpeed = currentSaveData.charSpeed,
            music = it,
            sound = currentSaveData.sound
        )
        updateUiState()
    }

    fun setNewSoundValue(it: Float) {

        currentSaveData = currentSaveData.copy(
            id = 1,
            sound = it
        )
        updateUiState()
    }

    fun setNewCharSpeedValue(it: Float) {
        currentSaveData = currentSaveData.copy(
            id = 1,
            charSpeed = it.roundToInt()
        )
        updateUiState()
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LoveEngine)
                LoveViewModel(application.database.novellDataDao())
            }
        }
    }
}