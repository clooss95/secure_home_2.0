package com.bonacode.securehome.domain.feature.settings.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import com.bonacode.securehome.domain.feature.settings.model.SettingsModel
import javax.inject.Inject

class GetSettings @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : UseCase<Unit, SettingsModel> {

    override suspend fun invoke(params: Unit): SettingsModel =
        settingsDataSource.getSettings()
}
