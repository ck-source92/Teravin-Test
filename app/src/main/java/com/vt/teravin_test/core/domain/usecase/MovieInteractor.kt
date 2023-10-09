package com.vt.teravin_test.core.domain.usecase

import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.domain.repository.IMovieRepository
import com.vt.teravin_test.core.source.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    IMovieUsecase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovie()
    }

    override fun getOfflineMovie(): Flow<List<Movie>> {
        return movieRepository.getMovies()
    }
}