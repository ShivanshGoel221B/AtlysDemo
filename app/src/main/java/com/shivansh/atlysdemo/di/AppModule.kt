package com.shivansh.atlysdemo.di

import com.shivansh.atlysdemo.data.repository.MoviesRepository
import com.shivansh.atlysdemo.domain.repository.MoviesRepositoryImpl
import com.shivansh.atlysdemo.domain.usecase.GetTrendingMoviesUseCase
import com.shivansh.atlysdemo.domain.usecase.FilterMoviesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::MoviesRepositoryImpl).bind(MoviesRepository::class)
    singleOf(::GetTrendingMoviesUseCase)
    singleOf(::FilterMoviesUseCase)
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}