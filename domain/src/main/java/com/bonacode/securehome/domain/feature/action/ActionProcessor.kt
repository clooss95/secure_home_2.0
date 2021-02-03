package com.bonacode.securehome.domain.feature.action

import com.bonacode.securehome.domain.feature.action.model.SmsSendResult

interface ActionProcessor {
    suspend fun processAction(message: String): SmsSendResult
}
