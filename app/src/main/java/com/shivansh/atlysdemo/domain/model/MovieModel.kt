package com.shivansh.atlysdemo.domain.model

import com.shivansh.atlysdemo.BuildConfig
import com.shivansh.atlysdemo.data.entity.MovieEntity

data class MovieModel(
    val id: Int,
    val posterUrl: String,
    val title: String,
    val description: String
)

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        id = id,
        title = title,
        description = overview,
        posterUrl = buildString {
            append(BuildConfig.POSTER_URL_PREFIX)
            append(posterPath)
        }
    )
}
