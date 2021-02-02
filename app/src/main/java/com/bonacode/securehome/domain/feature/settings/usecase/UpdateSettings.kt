package com.bonacode.securehome.domain.feature.settings.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import com.bonacode.securehome.domain.feature.settings.model.SettingsModel
import javax.inject.Inject

class UpdateSettings @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : UseCase<UpdateSettings.Params, Unit> {

    override suspend fun invoke(params: Params) {
        settingsDataSource.updateSettings(params.settingsModel)
    }

    data class Params(
        val settingsModel: SettingsModel
    )
}
