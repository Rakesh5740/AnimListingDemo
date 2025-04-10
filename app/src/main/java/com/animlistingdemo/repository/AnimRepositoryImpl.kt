package com.animlistingdemo.repository

import com.animlistingdemo.data.AnimDetails
import com.animlistingdemo.data.AnimItem
import com.animlistingdemo.network.ApiException
import com.animlistingdemo.network.ApiService
import com.animlistingdemo.network.ApiState
import com.animlistingdemo.network.suspendedNetworkGetRequest
import com.animlistingdemo.repository.mapper.AnimListMapper
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AnimRepository {

    override suspend fun getAnimData(): ApiState<List<AnimItem>> {
        return suspendedNetworkGetRequest(
            fetch = {
                apiService.getAnimList()
            },
            map = { response ->
                AnimListMapper.mapData(response)
            },
            onSuccess = {
            },
            onMappingFailure = { mappingFailure -> Timber.e(mappingFailure) },
            onApiFailure = { apiFailure -> Timber.e(ApiException(apiFailure)) },
            dispatcher = Dispatchers.IO
        )
    }

    override suspend fun getAnimDetails(id: Int): ApiState<AnimDetails> {
        return suspendedNetworkGetRequest(
            fetch = {
                apiService.getAnimDetails(id)
            },
            map = { response ->
                AnimListMapper.mapAnimDetails(response)
            },
            onSuccess = {
            },
            onMappingFailure = { mappingFailure -> Timber.e(mappingFailure) },
            onApiFailure = { apiFailure -> Timber.e(ApiException(apiFailure)) },
            dispatcher = Dispatchers.IO
        )
    }
}