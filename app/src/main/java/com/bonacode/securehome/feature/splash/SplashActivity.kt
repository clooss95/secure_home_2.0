package com.bonacode.securehome.feature.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bonacode.securehome.feature.setup.SetupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SetupActivity.start(this)
    }

}