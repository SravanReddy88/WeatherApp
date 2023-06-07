package com.sravan.weatherapp.data.repository

import com.sravan.weatherapp.data.models.BaseApiResponse
import com.sravan.weatherapp.data.models.GenericResponse
import com.sravan.weatherapp.data.remote.RemoteDataSource
import com.sravan.weatherapp.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getWeatherAvailable(
        city: String,
        stateCode: String,
        countryCode: String
    ): Flow<NetworkResult<GenericResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getWeatherAvailable(
                city,
                stateCode,
                countryCode
            ) })
        }.flowOn(Dispatchers.IO)
    }

}
