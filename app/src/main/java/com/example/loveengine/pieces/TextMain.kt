package com.example.loveengine.pieces

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.loveengine.data.defaultBlack
import com.example.loveengine.data.defaultLineHeight
import com.example.loveengine.data.defaultTextSize
import com.example.loveengine.data.largeDimen
import com.example.loveengine.data.largeTextSize
import com.example.loveengine.data.lowDimen
import com.example.loveengine.data.midDimen
import com.example.loveengine.data.middleLowDimen
import com.example.loveengine.data.nameOfCharacterTextSize
import com.example.loveengine.data.outlineColor
import com.example.loveengine.data.outlineDimen
import com.example.loveengine.pieces.core.LoveBox
import com.example.loveengine.pieces.core.LoveText
import kotlinx.coroutines.delay


@Composable
fun TextMain(
    modifier: Modifier = Modifier,
    nameOfCharacter: String,
    text: String, // maxLen == 250
    speed: Long = 15,
    onClick: () -> Unit = {},
    color: Color = Color.Black,
    textColor: Color = Color.White,
    enabled: Boolean = true
) {
    //Ui element, for texts in the bottom of main screen

    var realText by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        val splitText = text.toCharArray()
        realText = ""
        for (char in splitText) {
            if (realText.length <= text.length) {
                delay(speed)
                realText += char
            } else {
                realText = text
                break
            }
        }
    }

    Column(
        modifier = modifier,
        ) {
        Row {
            Spacer(modifier = Modifier.padding(end = largeDimen))
            Box (
                modifier = Modifier
                    .fillMaxHeight(0.25F)
                    .fillMaxWidth(0.2F)
                    .background(
                        color = defaultBlack,
                        shape = RoundedCornerShape(
                            topEndPercent = 50,
                            topStartPercent = 20,
                            bottomEndPercent = 0,
                            bottomStartPercent = 0
                        ),
                    )
                    .border(
                        width = outlineDimen,
                        shape = RoundedCornerShape(
                            topEndPercent = 50,
                            topStartPercent = 20,
                            bottomEndPercent = 0,
                            bottomStartPercent = 0
                        ),
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.5F to outlineColor,
                                0.5F to outlineColor
                            )
                        )
                    )
                ,
            ){
                LoveText(
                    text = nameOfCharacter,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Center),

                    textAlign = TextAlign.Center,
                    fontSize = nameOfCharacterTextSize,
                )
            }

        }
       
        LoveBox(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    enabled = enabled
                ) {
                    if (realText != text) {
                        realText = text
                    } else {
                        onClick()
                    }
                }
        ) {

                Text(
                    text = realText,
                    color = textColor,
                    modifier = Modifier
                        .align(CenterStart)
                        .padding(start = middleLowDimen, end = middleLowDimen),
                    fontSize = defaultTextSize,
                    lineHeight = defaultLineHeight
                )

        }
    }

}
