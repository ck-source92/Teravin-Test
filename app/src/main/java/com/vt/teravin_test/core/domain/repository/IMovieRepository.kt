package com.vt.teravin_test.core.domain.repository

import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
}