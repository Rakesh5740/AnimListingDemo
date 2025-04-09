package com.animlistingdemo.repository

import com.animlistingdemo.data.EmployeeList
import com.animlistingdemo.network.ApiException
import com.animlistingdemo.network.ApiService
import com.animlistingdemo.network.ApiState
import com.animlistingdemo.network.suspendedNetworkGetRequest
import com.animlistingdemo.repository.mapper.EmployeeListMapper
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : EmployeeRepository {

    override suspend fun getEmployeeDetails(): ApiState<EmployeeList> {
        return suspendedNetworkGetRequest(
            fetch = {
                apiService.getEmployeeList()
            },
            map = { response ->
                EmployeeListMapper.mapData(response)
            },
            onSuccess = {
            },
            onMappingFailure = { mappingFailure -> Timber.e(mappingFailure) },
            onApiFailure = { apiFailure -> Timber.e(ApiException(apiFailure)) },
            dispatcher = Dispatchers.IO
        )
    }
}