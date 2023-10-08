package com.vt.teravin_test.core.source

import com.vt.teravin_test.DataMapper
import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.domain.repository.IMovieRepository
import com.vt.teravin_test.core.source.local.LocalDataSource
import com.vt.teravin_test.core.source.remote.network.ApiResponse
import com.vt.teravin_test.core.source.remote.response.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeravinRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDatabase(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun fetchDataFromDatabase(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> {
                println("hello repos 2")
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movieEntity = DataMapper.mapResponseToEntity(data)
                localDataSource.insertMovieToLocal(movieEntity)
            }
        }.asFlow()
}