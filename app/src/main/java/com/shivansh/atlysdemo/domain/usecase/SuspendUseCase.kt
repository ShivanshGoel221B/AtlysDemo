package com.shivansh.atlysdemo.domain.usecase

import com.shivansh.atlysdemo.utils.AtlysResult

interface SuspendUseCase<T> {
    suspend fun launch(): AtlysResult<T>
}