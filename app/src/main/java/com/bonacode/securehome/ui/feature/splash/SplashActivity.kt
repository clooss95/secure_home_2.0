package com.bonacode.securehome.ui.feature.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import com.bonacode.securehome.ui.feature.main.activity.MainActivity
import com.bonacode.securehome.ui.feature.setup.SetupActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var settingsDataSource: SettingsDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (settingsDataSource.isConfigurationDone()) {
            MainActivity.start(this)
        } else {
            SetupActivity.start(this)
        }

        finish()
    }
}
