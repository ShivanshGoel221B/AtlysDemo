package com.shivansh.atlysdemo.data.repository

import com.shivansh.atlysdemo.data.entity.MoviesResponseEntity
import com.shivansh.atlysdemo.domain.model.MovieModel
import com.shivansh.atlysdemo.utils.AtlysResult

interface MoviesRepository {

    suspend fun getTrendingMovies(): AtlysResult<MoviesResponseEntity>
}