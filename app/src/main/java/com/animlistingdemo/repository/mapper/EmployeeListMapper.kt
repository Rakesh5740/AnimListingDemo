package com.animlistingdemo.repository.mapper

import com.animlistingdemo.data.Employee
import com.animlistingdemo.data.EmployeeList
import com.animlistingdemo.data.EmployeeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object EmployeeListMapper {

    suspend fun mapData(
        employeeResponse: EmployeeResponse,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): EmployeeList = withContext(dispatcher) {
        val data = employeeResponse.employeeList?.map { data ->
            object : Employee {
                override val uuid: String
                    get() = data.uuid ?: ""
                override val fullName: String
                    get() = data.fullName ?: ""
                override val email: String
                    get() = data.email ?: ""
                override val jobTitle: String
                    get() = data.phoneNumber ?: ""
                override val thumbNail: String
                    get() = data.photoUrlSmall ?: ""
                override val team: String
                    get() = data.team ?: ""
            }
        }
        object : EmployeeList {
            override val employeeList: List<Employee?>
                get() = data ?: emptyList()
        }
    }
}