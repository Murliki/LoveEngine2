package com.example.loveengine.pieces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loveengine.R
import com.example.loveengine.classes.MenuDialog
import com.example.loveengine.data.spacerForChoiceDialog
import com.example.loveengine.data.spacerSizeInt
import com.example.loveengine.pieces.core.LoveBox
import com.example.loveengine.pieces.core.LoveText

@Composable
fun ChoiceDialog(
    modifier: Modifier = Modifier,
    menuDialog: MenuDialog,
    spacerSize: Dp = spacerSizeInt.dp,
    changeBranch: (Int) -> Unit = {},
    nextData: () -> Unit = {}
) {
    //Ui element for dialog

    @Composable
    fun ChoiceElement(
        //Ui element for one element in dialog
        modifier: Modifier = Modifier,
        onClick: () -> Unit,
        text: String
    ) {
        LoveBox(
            modifier = modifier
                .clickable(onClick = { onClick() })
        ) {
            LoveText(text = text, modifier = Modifier.align(Alignment.Center))
        }
    }



    Box(
        modifier = modifier.background(
            color = Color(0x00FFFFFF),
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            menuDialog.fourthChoice?.let {
                ChoiceElement(
                    modifier = Modifier.padding(start = spacerForChoiceDialog.dp),
                    onClick = {
                        menuDialog.fourthOnCLick()
                        changeBranch(menuDialog.fourthChangeBranch)
                        nextData()
                    },
                    text = it
                )
                Spacer(modifier = Modifier.height(spacerSize))
            }

            menuDialog.thirdChoice?.let {
                ChoiceElement(
                    modifier = Modifier.padding(
                        start = ((spacerForChoiceDialog / 3) * 2).dp,
                        end = (spacerForChoiceDialog / 3).dp
                    ),
                    onClick = {
                        menuDialog.thirdOnCLick()
                        changeBranch(menuDialog.thirdChangeBranch)
                        nextData()
                    },
                    text = it
                )
                Spacer(modifier = Modifier.height(spacerSize))
            }

            menuDialog.secondChoice?.let {
                ChoiceElement(
                    modifier = Modifier.padding(
                        start = ((spacerForChoiceDialog / 3).dp),
                        end = ((spacerForChoiceDialog / 3) * 2).dp
                    ),
                    onClick = {
                        menuDialog.secondOnCLick()
                        changeBranch(menuDialog.secondChangeBranch)
                        nextData()
                    },
                    text = it
                )
                Spacer(modifier = Modifier.height(spacerSize))
            }
            ChoiceElement(
                modifier = Modifier.padding(end = spacerForChoiceDialog.dp),
                onClick = {
                    menuDialog.firstOnClick()
                    changeBranch(menuDialog.firstChangeBranch)
                    nextData()
                },
                text = menuDialog.firstChoice
            )
        }
    }
}

@Preview(widthDp = 720, heightDp = 360)
@Composable
private fun ChoiceDialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.background_classroom_1),
            contentDescription = null, //TODO
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            ChoiceDialog(
                modifier = Modifier
                    .fillMaxWidth(0.2F)
                    .fillMaxHeight(0.3F),
                menuDialog = MenuDialog(
                    firstChoice = "Yes", firstOnClick = {},
                    secondChoice = "Tara",
                    thirdChoice = "NO",
                    fourthChoice = "definitely no",
                ),
                changeBranch = {}
            )
            Spacer(modifier = Modifier.padding(32.dp))
        }

    }
}