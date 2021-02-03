package com.bonacode.securehome.domain.feature.action

interface ActionCommandBuilderProvider {
    fun provideActionCommandBuilder(): ActionCommandBuilder
}
