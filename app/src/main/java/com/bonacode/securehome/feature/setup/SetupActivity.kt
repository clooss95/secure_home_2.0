package com.bonacode.securehome.feature.setup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bonacode.securehome.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SetupActivity::class.java))
        }
    }
}