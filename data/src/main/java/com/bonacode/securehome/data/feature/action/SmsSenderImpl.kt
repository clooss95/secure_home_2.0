package com.bonacode.securehome.data.feature.action

import android.telephony.SmsManager
import com.bonacode.securehome.domain.feature.action.SmsSender
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult
import javax.inject.Inject

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
