package com.busol.habittracker.core.ui.components

interface UseCase<out Type: Any>

interface UseCaseParams
interface UseCaseWithParams<in Params : UseCaseParams, out Type : Any> : UseCase<Type> {
    suspend operator fun invoke(params: Params): Type
}

interface UseCaseWithNoParams<out Type : Any> : UseCase<Type> {
    suspend operator fun invoke(): Type
}