package com.bonacode.securehome.domain.common

interface UseCase<in T, out R> {

    suspend fun invoke(params: T): R
}

suspend fun <R> UseCase<Unit, R>.invoke(): R = invoke(Unit)
