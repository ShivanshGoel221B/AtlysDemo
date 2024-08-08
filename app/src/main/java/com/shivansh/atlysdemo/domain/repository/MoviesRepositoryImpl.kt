package com.shivansh.atlysdemo.domain.repository

import com.shivansh.atlysdemo.BuildConfig
import com.shivansh.atlysdemo.data.entity.MoviesResponseEntity
import com.shivansh.atlysdemo.data.repository.MoviesRepository
import com.shivansh.atlysdemo.utils.AtlysResult
import com.shivansh.atlysdemo.utils.atlysResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRepositoryImpl: MoviesRepository, KoinComponent {

    private val client: HttpClient by inject()

    override suspend fun getTrendingMovies(): AtlysResult<MoviesResponseEntity> = atlysResult {
        val response = client.get {
            url(BuildConfig.MOVIES_URL)
            headers {
                bearerAuth(BuildConfig.MOVIES_ACCESS_TOKEN)
                accept(ContentType.Application.Json)
            }
        }
        if (response.status == HttpStatusCode.OK) {
            return@atlysResult response.body()
        } else {
            throw Exception()
        }
    }
}

