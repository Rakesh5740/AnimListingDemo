package com.animlistingdemo.repository
import com.animlistingdemo.data.EmployeeList
import com.animlistingdemo.network.ApiState

interface EmployeeRepository {

    suspend fun getEmployeeDetails(): ApiState<EmployeeList>

}