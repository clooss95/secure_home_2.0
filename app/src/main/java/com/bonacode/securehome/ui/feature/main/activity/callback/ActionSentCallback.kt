package com.bonacode.securehome.ui.feature.main.activity.callback

import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult

interface ActionSentCallback {
    enum class ActionSentSource {
        HOME, CONTROL_PANEL, HISTORY
    }

    fun actionSent(action: ActionModel, source: ActionSentSource, result: SmsSendResult)
}
