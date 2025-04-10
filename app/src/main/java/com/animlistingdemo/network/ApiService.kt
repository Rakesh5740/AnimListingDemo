package com.animlistingdemo.network

import com.animlistingdemo.data.AnimDetailsResponse
import com.animlistingdemo.data.MyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v4/top/anime")
    suspend fun getAnimList(): Response<MyResponse>

    @GET("v4/anime/{anime_id}")
    suspend fun getAnimDetails(@Path("anime_id") id: Int): Response<AnimDetailsResponse>

}