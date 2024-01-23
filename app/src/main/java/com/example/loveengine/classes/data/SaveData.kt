package com.example.loveengine.classes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Save_data")
data class SaveData(

    @PrimaryKey
    @ColumnInfo(defaultValue = "0")
    var id: Int = 0,

    @ColumnInfo(defaultValue = "0")
    var day: Int = 0,

    @ColumnInfo(defaultValue = "0")
    var number: Int = 0,

    @ColumnInfo(defaultValue = "25")
    var charSpeed: Int = 25,

    @ColumnInfo(defaultValue = "1.0")
    val music: Float = 1F,

    @ColumnInfo(defaultValue = "1.0")
    val sound: Float = 1F,

)
