package com.bonacode.securehome.domain.feature.action.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult

interface BuildActionUseCase<in T, out R> : UseCase<T, R> {
    val actionType: ActionType
}

interface ActionUseCase<in T> : UseCase<T, Pair<SmsSendResult, ActionModel>> {
    val actionType: ActionType
}
