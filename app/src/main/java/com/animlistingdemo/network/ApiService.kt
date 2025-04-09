package com.animlistingdemo.network

import com.animlistingdemo.data.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("employees_malformed.json")
    suspend fun getEmployeeList(): Response<EmployeeResponse>

}