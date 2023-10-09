package com.vt.teravin_test.core.source.local

import com.vt.teravin_test.core.source.local.entity.MovieEntity
import com.vt.teravin_test.core.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()
    suspend fun insertMovieToLocal(movie: List<MovieEntity>) = movieDao.insertMovie(movie)
    fun getOfflineMovie(): Flow<List<MovieEntity>> = movieDao.getOfflineMovie()
}
