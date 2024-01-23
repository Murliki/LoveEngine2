package com.example.loveengine

import android.app.Application
import com.example.loveengine.data.NovellDataDatabase

class LoveEngine: Application() {
    //add database to application
    val database by lazy { NovellDataDatabase.getDatabase(this) }

}