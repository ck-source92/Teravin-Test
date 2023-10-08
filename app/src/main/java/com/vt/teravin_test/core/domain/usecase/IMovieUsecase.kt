package com.vt.teravin_test.core.domain.usecase

import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieUsecase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
}