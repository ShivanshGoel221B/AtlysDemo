package com.shivansh.atlysdemo.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity(
    @SerialName("id")
    val id: Int,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("title")
    val title: String
)