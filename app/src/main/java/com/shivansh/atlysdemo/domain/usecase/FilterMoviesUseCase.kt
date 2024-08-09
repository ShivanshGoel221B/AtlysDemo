package com.shivansh.atlysdemo.domain.usecase

import com.shivansh.atlysdemo.data.repository.MoviesRepository
import com.shivansh.atlysdemo.domain.model.MovieModel
import com.shivansh.atlysdemo.domain.model.toModel
import com.shivansh.atlysdemo.utils.AtlysResult
import com.shivansh.atlysdemo.utils.mapResult

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