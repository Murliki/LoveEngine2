package com.example.loveengine.classes.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Saved_environment")
data class SavedEnvironment(
    //additional save class

    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val background: Int? = null,
    val music: Int? = null,

    val character1: String? = null,
    val character2: String? = null,
    val character3: String? = null,

    val emotion1: String? = null,
    val emotion2: String? = null,
    val emotion3: String? = null,

    val dialog: Int? = null,
    var setter: Int? = null

    )