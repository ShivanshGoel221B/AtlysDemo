package com.shivansh.atlysdemo.domain.usecase

import com.shivansh.atlysdemo.domain.model.MovieModel

class FilterMoviesUseCase {

    operator fun invoke(
        movies: List<MovieModel>,
        query: String
    ): List<MovieModel> {
        if (query.isBlank()) return movies
        val transformedQueries = listOf(
            query.split(',', ' ', '-'),
            query.split(' '),
            query.split(',', ' ')
        ).flatten() + query
        return movies.filter { movie ->
            val title = movie.title
            transformedQueries.any {
                title.contains(it, ignoreCase = true)
            }
        }
    }
}