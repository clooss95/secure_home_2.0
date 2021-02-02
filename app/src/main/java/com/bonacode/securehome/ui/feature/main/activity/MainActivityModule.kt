package com.bonacode.securehome.ui.feature.main.activity

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class MainActivityModule {
    @Provides
    fun provideActionSentCallback(@ActivityContext context: Context): ActionSentCallback =
        ViewModelProvider(context as MainActivity).get(MainViewModel::class.java)

    @Provides
    fun provideSaveFavouriteActionCallback(@ActivityContext context: Context): SaveFavouriteActionCallback =
        ViewModelProvider(context as MainActivity).get(MainViewModel::class.java)
}
