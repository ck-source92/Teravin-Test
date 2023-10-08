package com.vt.teravin_test.core.source

import com.vt.teravin_test.core.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val data = loadFromDatabase().first()
        if (fetchDataFromDatabase(data)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDatabase().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDatabase().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDatabase(): Flow<ResultType>
    protected abstract fun fetchDataFromDatabase(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}