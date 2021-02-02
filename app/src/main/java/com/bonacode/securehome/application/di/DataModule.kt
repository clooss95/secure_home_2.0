package com.bonacode.securehome.application.di

import com.bonacode.securehome.data.feature.actionhistory.repository.ActionHistoryRepository
import com.bonacode.securehome.data.feature.favouriteaction.repositroy.FavouriteActionRepository
import com.bonacode.securehome.data.feature.settings.repository.SettingsRepository
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import com.bonacode.securehome.domain.feature.favouriteaction.datasource.FavouriteActionDataSource
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindSettingsDataSource(impl: SettingsRepository): SettingsDataSource

    @Singleton
    @Binds
    fun bindActionHistoryDataSource(impl: ActionHistoryRepository): ActionHistoryDataSource

    @Singleton
    @Binds
    fun bindFavouriteActionDataSource(impl: FavouriteActionRepository): FavouriteActionDataSource
}
