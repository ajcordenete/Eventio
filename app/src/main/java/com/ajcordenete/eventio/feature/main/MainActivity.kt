package com.ajcordenete.eventio.feature.main

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ajcordenete.eventio.R
import com.ajcordenete.eventio.databinding.ActivityMainBinding
import com.ajcordenete.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable support for Splash Screen API for
        // proper Android 12+ support
        installSplashScreen()

        super.onCreate(savedInstanceState)
    }
}