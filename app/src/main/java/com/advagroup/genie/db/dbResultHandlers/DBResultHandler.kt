package com.advagroup.genie.db.dbResultHandlers

sealed class DBResultHandler<out T> {
    data class Success<out T>(val data: T): DBResultHandler<T>()
    data class Error(val message: String, val throwable: Throwable? = null): DBResultHandler<Nothing>()
}