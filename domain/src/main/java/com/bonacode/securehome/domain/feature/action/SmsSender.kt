package com.bonacode.securehome.domain.feature.action

import com.bonacode.securehome.domain.feature.action.model.SmsSendResult

interface SmsSender {
    fun sendSms(message: String, phoneNumber: String): SmsSendResult
}
