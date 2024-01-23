package com.example.loveengine.classes.data

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


//EXPECTED
@Entity(tableName = "Novell_data")
data class NovellData(
    //class that use for ALL data and all possible variants of game
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0, //= 0

    @ColumnInfo(name = "text")
    val text: String? = "",

    //what name will be lighted on the next dialog
    @NotNull
    @ColumnInfo(name = "speaker")
    val speaker: String,

    //add character in the cell 1-3
    @ColumnInfo(name = "add_character1", defaultValue = "null")
    val addCharacter1: String? = null,

    @ColumnInfo(name = "add_character2", defaultValue = "null")
    val addCharacter2: String? = null,

    @ColumnInfo(name = "add_character3", defaultValue = "null")
    val addCharacter3: String? = null,


    //set the character emotion in the cell 1-3
    @ColumnInfo(name = "set_emotion1", defaultValue = "null")
    val setEmotion1: String? = null,

    @ColumnInfo(name = "set_emotion2", defaultValue = "null")
    val setEmotion2: String? = null,

    @ColumnInfo(name = "set_emotion3", defaultValue = "null")
    val setEmotion3: String? = null,


    @ColumnInfo(name = "dialog", defaultValue = "null")
    val dialog: Int? = null,

    @ColumnInfo(name = "set_new_background", defaultValue = "null")
    val setNewBackground: Int? = null,

    @ColumnInfo(name = "set_new_music", defaultValue = "null")
    val setNewMusic: Int? = null, //TODO

    @ColumnInfo(name = "delete_character", defaultValue = "null")
    val deleteCharacter: Int? = null,


    @NotNull
    @ColumnInfo(name = "delete_all_characters", defaultValue = "0")
    val deleteAllCharacters: Int = 0,

    //variables for future functions (255 possible combinations)
    @ColumnInfo(name = "setter", defaultValue = "0")
    val setter: Int = 0
)

