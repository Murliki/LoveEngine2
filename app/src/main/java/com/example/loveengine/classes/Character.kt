package com.example.loveengine.classes


data class Character(
    val name: String,
    private val defaultEmoji: Int,
    private val horny: Int? = null,
    private val boring: Int? = null,
    private val excited: Int? = null,
    private val happy: Int? = null,
    private val scared: Int? = null,
) {
    //class to set and control all sprites of one character
    private var currentEmoji: Int = defaultEmoji

    fun setHorny() { currentEmoji = horny ?: currentEmoji }
    fun setBoring() {currentEmoji = boring ?: currentEmoji }
    fun setExcited() { currentEmoji = excited ?: currentEmoji }
    fun setHappy() { currentEmoji = happy ?: currentEmoji }
    fun setDefault() { currentEmoji = defaultEmoji }
    fun setScared() { currentEmoji = scared ?: currentEmoji }
    fun currentEmoji(): Int { return currentEmoji }

}