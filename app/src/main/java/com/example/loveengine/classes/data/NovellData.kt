package com.example.loveengine.classes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//EXPECTED
@Entity(tableName = "Novell_data")
data class NovellData(
    //class that use for ALL data and all possible variants of game

    //internal id for interaction with the novellData
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0, //= 0

    @ColumnInfo(name = "text")
    val text: String? = "",

    //what name will be on top of the TextMain. Could be any string
    @ColumnInfo(name = "speaker")
    val speaker: String,

    //add character in the cell 1-3
    @ColumnInfo(name = "add_character1", defaultValue = "null")
    val addCharacter1: String? = null,

    @ColumnInfo(name = "add_character2", defaultValue = "null")
    val addCharacter2: String? = null,

    @ColumnInfo(name = "add_character3", defaultValue = "null")
    val addCharacter3: String? = null,


    /*set the character emotion in the cell 1-3, database name should be a
    string with name of emotion, that presented in loveengine/classes/Emotion
    */
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


    @ColumnInfo(name = "delete_all_characters", defaultValue = "0")
    val deleteAllCharacters: Int = 0,

    /*Variable for actions that happens not very often()
    * use:
    *   viewModel
    *
    *   if(setter == actionNumber){
    *       actionFun
    *   }
    * */
    @ColumnInfo(name = "setter", defaultValue = "0")
    val setter: Int = 0
)

