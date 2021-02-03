package com.bonacode.securehome.application.di

import com.bonacode.securehome.data.feature.action.ActionCommandBuilderProviderImpl
import com.bonacode.securehome.data.feature.action.ActionProcessorImpl
import com.bonacode.securehome.data.feature.action.SmsSenderImpl
import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.ActionProcessor
import com.bonacode.securehome.domain.feature.action.SmsSender
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface SmsCommunicationModule {

    @Binds
    fun bindActionCommandBuilderProvider(impl: ActionCommandBuilderProviderImpl): ActionCommandBuilderProvider

    @Binds
    fun bindActionMainProcessor(impl: ActionProcessorImpl): ActionProcessor

    @Binds
    fun bindSmsSender(impl: SmsSenderImpl): SmsSender
}
