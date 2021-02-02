package com.bonacode.securehome.ui.feature.setup

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.bonacode.securehome.R
import com.bonacode.securehome.application.architecture.mvvm.ViewModelActivity
import com.bonacode.securehome.databinding.ActivitySetupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupActivity : ViewModelActivity<SetupViewModel, ActivitySetupBinding>(
    R.layout.activity_setup,
    R.id.navHostSetupActivity
) {
    override val viewModel: SetupViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SetupActivity::class.java))
        }
    }
}
