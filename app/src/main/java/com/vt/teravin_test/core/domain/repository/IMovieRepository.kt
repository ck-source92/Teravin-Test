package com.vt.teravin_test.core.domain.repository

import androidx.lifecycle.LiveData
import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getMovies(): Flow<List<Movie>>
}