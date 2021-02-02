package com.bonacode.securehome.application.di

import com.bonacode.securehome.application.smscommunication.ActionProcessor
import com.bonacode.securehome.application.smscommunication.ActionProcessorImpl
import com.bonacode.securehome.application.smscommunication.SmsSender
import com.bonacode.securehome.application.smscommunication.SmsSenderImpl
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProvider
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProviderImpl
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
