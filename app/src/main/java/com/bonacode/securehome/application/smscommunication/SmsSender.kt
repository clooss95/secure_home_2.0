package com.bonacode.securehome.application.smscommunication

import android.telephony.SmsManager
import javax.inject.Inject

enum class SmsSendResult {
    SUCCESS, FAILURE
}

interface SmsSender {
    fun sendSms(message: String, phoneNumber: String): SmsSendResult
}

class SmsSenderImpl @Inject constructor() : SmsSender {
    override fun sendSms(message: String, phoneNumber: String): SmsSendResult =
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            SmsSendResult.SUCCESS
        } catch (e: Exception) {
            SmsSendResult.FAILURE
        }
}
