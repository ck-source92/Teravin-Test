package com.vt.teravin_test.core.source.remote.network

import com.vt.teravin_test.core.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
    ): MovieResponse
}