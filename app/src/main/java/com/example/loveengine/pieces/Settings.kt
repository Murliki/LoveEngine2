package com.example.loveengine.pieces

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.loveengine.R
import com.example.loveengine.data.borderColor
import com.example.loveengine.data.defaultBlack
import com.example.loveengine.data.lowDimen
import com.example.loveengine.data.normalDimen
import com.example.loveengine.data.outlineColor
import com.example.loveengine.data.outlineDimen
import com.example.loveengine.data.textColor
import kotlin.math.roundToInt

@Composable
fun NovellSettingsButton(
    //Ui element for button, that opens settings
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = defaultBlack, shape = CircleShape)
            .border(BorderStroke(outlineDimen, Color.White), shape = CircleShape)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.settings_white),
            tint = Color.White,
            contentDescription = "Settings",
            modifier = Modifier
                .padding(2.dp)
                .clickable { onClick() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    progressMusic: Float,
    onValueChangeMusic: (Float) -> Unit,
    progressSound: Float,
    onValueChangeSound: (Float) -> Unit,
    progressTextSpeed: Float,
    onValueChangeTextSpeed: (Float) -> Unit,
) {

    val sliderColors = SliderDefaults.colors(
        activeTrackColor = Color.White,
        inactiveTickColor = Color.Black,
        inactiveTrackColor = Color.Black
    )

    //Ui element for settings
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Box(
            modifier = modifier
                .border(
                    border = BorderStroke(
                        outlineDimen,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(normalDimen)
                )
                .background(color = defaultBlack, shape = RoundedCornerShape(normalDimen))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            )
            {

                Spacer(modifier = Modifier.padding(lowDimen))
                Box(
                    modifier = Modifier.fillMaxWidth(0.9F)
                ) {

                    Text(
                        text = stringResource(R.string.music),
                        color = textColor,
                        modifier = Modifier.align(TopEnd),
                        fontSize = 10.sp
                    )
                    Text(
                        text = "${progressMusic.roundToInt()}",
                        color = textColor,
                        modifier = Modifier.align(TopCenter),
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.padding(lowDimen))

                    Slider(
                        value = progressMusic,
                        onValueChange = { onValueChangeMusic(it) },
                        modifier = Modifier
                            .fillMaxWidth(0.85F)
                            .align(TopCenter),
                        steps = 99,
                        valueRange = 0F..100F,
                        colors = sliderColors,
                        thumb = {
                            Box(
                                modifier = Modifier.border(
                                    BorderStroke(
                                        outlineDimen,
                                        outlineColor
                                    ),
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        if (progressMusic != 0F) R.drawable.baseline_music_note_24
                                        else R.drawable.baseline_music_off_24
                                    ),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(0.9F)) {

                    Text(
                        text = stringResource(R.string.sound),
                        color = textColor,
                        modifier = Modifier.align(TopEnd),
                        fontSize = 10.sp
                    )
                    Text(
                        text = "${progressSound.roundToInt()}",
                        color = textColor,
                        modifier = Modifier.align(TopCenter),
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.padding(lowDimen))

                    Slider(
                        value = progressSound,
                        onValueChange = { onValueChangeSound(it) },
                        modifier = Modifier
                            .fillMaxWidth(0.85F)
                            .align(TopCenter),
                        steps = 99,
                        colors = sliderColors,
                        valueRange = 0F..100F,
                        thumb = {
                            Box(
                                modifier = Modifier.border(
                                    BorderStroke(
                                        outlineDimen,
                                        outlineColor
                                    ),
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        if (progressSound > 50F) R.drawable.baseline_volume_up_24
                                        else if (progressSound > 0F) R.drawable.baseline_volume_down_24
                                        else R.drawable.baseline_volume_mute_24
                                    ),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(0.9F)) {

                    Text(
                        text = stringResource(R.string.text_speed),
                        color = textColor,
                        modifier = Modifier.align(TopEnd),
                        fontSize = 10.sp
                    )
                    Text(
                        text = "${progressTextSpeed.roundToInt()}",
                        color = textColor,
                        modifier = Modifier.align(TopCenter),
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.padding(lowDimen))

                    Slider(
                        value = progressTextSpeed,
                        onValueChange = { onValueChangeTextSpeed(it) },
                        modifier = Modifier
                            .fillMaxWidth(0.85F)
                            .align(TopCenter),
                        steps = 24,
                        colors = sliderColors,
                        valueRange = 5F..30F,
                        thumb = {
                            Box(
                                modifier = Modifier.border(
                                    BorderStroke(
                                        outlineDimen,
                                        outlineColor
                                    ),
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.baseline_speed_24),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SliderPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xAA000000))
    ){
        Slider(
            value = 70F,
            onValueChange = { },
            steps = 99,
            modifier = Modifier
                .fillMaxWidth(0.85F)
                .align(TopCenter),
            valueRange = 0F..100F,
            colors = SliderDefaults.colors(
                activeTrackColor = Color.White,
                inactiveTickColor = Color.Black,
                inactiveTrackColor = Color.Black
            ),
            thumb = {
                Box(
                    modifier = Modifier.border(
                        BorderStroke(
                            outlineDimen,
                            outlineColor
                        ),
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            R.drawable.baseline_music_note_24
                        ),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
    }
}
