package com.shivansh.atlysdemo.domain.usecase

import com.shivansh.atlysdemo.data.repository.MoviesRepository
import com.shivansh.atlysdemo.domain.model.MovieModel
import com.shivansh.atlysdemo.domain.model.toModel
import com.shivansh.atlysdemo.utils.AtlysResult
import com.shivansh.atlysdemo.utils.mapResult

class GetTrendingMoviesUseCase(
    private val repository: MoviesRepository
) {

    suspend operator fun invoke(): AtlysResult<List<MovieModel>> {
        return repository.getTrendingMovies().mapResult { result ->
            result.results.map { it.toModel() }
        }
    }
}