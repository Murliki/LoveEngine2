package com.example.loveengine.test

import com.example.loveengine.R
import com.example.loveengine.classes.Character
import com.example.loveengine.classes.data.NovellData

val anya: Character = Character(
    name = "Anya",
    defaultEmoji = R.drawable.girl1_ready,
    horny = R.drawable.girl1_horny,
    happy = R.drawable.girl1_happy,
    boring = R.drawable.girl1_boring,
    excited = R.drawable.girl1_excited
)

val errorData = NovellData(
    id = 9999,
    text = "ERROR",
    speaker = "ERROR"
)