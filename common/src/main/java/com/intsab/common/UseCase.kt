package com.intsab.common

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
abstract class UseCase<Type, in Params> where Type : Any {

    companion object {
        private const val TAG = "UseCase"
    }

    abstract suspend fun run(params: Params?): Type
}