package com.animlistingdemo.repository
import com.animlistingdemo.data.AnimDetails
import com.animlistingdemo.data.AnimItem
import com.animlistingdemo.network.ApiState

interface AnimRepository {

    suspend fun getAnimData(): ApiState<List<AnimItem>>

    suspend fun getAnimDetails(id: Int): ApiState<AnimDetails>

}