package com.example.loveengine.classes


class MenuDialog(
    val firstChoice: String,
    val secondChoice: String? = null,
    val thirdChoice: String? = null,
    val fourthChoice: String? = null,

    val firstOnClick: () -> Unit = {},
    val secondOnCLick: () -> Unit = {},
    val thirdOnCLick: () -> Unit = {},
    val fourthOnCLick: () -> Unit = {},

    //ids of branches in database
    val firstChangeBranch: Int = 0,
    val secondChangeBranch: Int = 0,
    val thirdChangeBranch: Int = 0,
    val fourthChangeBranch: Int = 0
) {

    companion object {
        /*this companion contains all dialogs.
        They can be activated from string in Database from viewModel*/
        val test = MenuDialog(
            firstChoice = "Bed",
            secondChoice = "Nowhere",
            firstChangeBranch = 6,
            secondChangeBranch = 9
        )
    }

}
