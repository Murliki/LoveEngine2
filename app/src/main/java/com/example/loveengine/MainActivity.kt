package com.example.loveengine

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loveengine.classes.Screens
import com.example.loveengine.screens.LoadingScreen
import com.example.loveengine.screens.MainScreen
import com.example.loveengine.screens.StartScreen
import com.example.loveengine.ui.LoveViewModel

class MainActivity : ComponentActivity() {

    //TODO set viewModel Here

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            //Starting
//            setKeepOnScreenCondition{
//                viewModelActions
//            }

        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Set real full screen mode for phones, with floating camera
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE



        setContent {
            val loveViewModel: LoveViewModel = viewModel(factory = LoveViewModel.factory)

            CurrentScreen(loveViewModel)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        //When the window is finally completed
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus)
            hideSystemUI()
    }

    //Hide upper and lower panel
    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //API >= 28
            window.insetsController?.let { controller ->
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                controller.hide(WindowInsets.Type.systemBars())
            }
        } else {
            //API < 28
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
    }
}

@Composable
private fun CurrentScreen(loveViewModel: LoveViewModel) {

    val loveUiState by loveViewModel.uiState.collectAsState()

    //Navigation between screens
    when (loveUiState.currentScreen) {

        Screens.MAIN -> MainScreen(loveViewModel = loveViewModel, loveUiState)
        Screens.START -> StartScreen(loveViewModel = loveViewModel, loveUiState = loveUiState)
        Screens.LOADING -> { LoadingScreen(loveViewModel = loveViewModel, loveUiState = loveUiState) }

    }

}