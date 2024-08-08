package com.shivansh.atlysdemo.utils

sealed class AtlysResult<T> {
    data class Failure<T>(val exception: Exception): AtlysResult<T>()
    data class Success<T>(val data: T): AtlysResult<T>()
}

inline fun <T> atlysResult(block: () -> T): AtlysResult<T> {
    return try {
        AtlysResult.Success(block())
    } catch (e: Exception) {
        AtlysResult.Failure(e)
    }
}

inline fun <U, V> AtlysResult<U>.mapResult(block: (U) -> V): AtlysResult<V> = when(this) {
    is AtlysResult.Success -> AtlysResult.Success(block(data))
    is AtlysResult.Failure -> AtlysResult.Failure(exception)
}