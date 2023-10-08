package com.vt.teravin_test.core.source

import android.util.Log
import com.vt.teravin_test.api_key
import com.vt.teravin_test.core.source.remote.network.ApiResponse
import com.vt.teravin_test.core.source.remote.network.ApiService
import com.vt.teravin_test.core.source.remote.response.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getAllMovie(): Flow<ApiResponse<List<MovieItem>>> {
        return flow {
            try {
                val response = apiService.getMovie(api_key)
                val dataArray = response.results
                println("result = ${dataArray}")
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}