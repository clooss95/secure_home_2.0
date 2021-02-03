package com.bonacode.securehome.domain.common

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<in T, out R> {

    fun invoke(params: T): Flow<R>
}

fun <R> FlowUseCase<Unit, R>.invoke(): Flow<R> = invoke(Unit)
