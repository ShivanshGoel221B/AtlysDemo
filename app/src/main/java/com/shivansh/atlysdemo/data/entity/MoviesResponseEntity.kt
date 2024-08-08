package com.shivansh.atlysdemo.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseEntity(
    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val results: List<MovieEntity>
)