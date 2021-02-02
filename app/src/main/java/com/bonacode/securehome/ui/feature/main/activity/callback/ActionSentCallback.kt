package com.bonacode.securehome.ui.feature.main.activity.callback

import com.bonacode.securehome.application.smscommunication.SmsSendResult
import com.bonacode.securehome.domain.feature.action.model.ActionModel

interface ActionSentCallback {
    enum class ActionSentSource {
        HOME, CONTROL_PANEL, HISTORY
    }

    fun actionSent(action: ActionModel, source: ActionSentSource, result: SmsSendResult)
}
