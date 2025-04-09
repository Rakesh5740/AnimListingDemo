package com.animlistingdemo.data

import com.animlistingdemo.Constant
import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName(Constant.Employee.EMPLOYEE_LIST)
    var employeeList: List<EmployeeData>? = null
)

data class EmployeeData(
    @SerializedName(Constant.Employee.UUID)
    var uuid: String? = null,
    @SerializedName(Constant.Employee.FULL_NAME)
    var fullName: String? = null,
    @SerializedName( Constant.Employee.EMAIL_ADDRESS)
    var email: String? = null,
    @SerializedName(Constant.Employee.PHONE_NUMBER)
    var phoneNumber: String? = null,
    @SerializedName(Constant.Employee.BIOGRAPHY)
    var biography: String? = null,
    @SerializedName(Constant.Employee.TEAM)
    var team: String? = null,
    @SerializedName(Constant.Employee.EMPLOYEE_TYPE)
    var employeeType: String? = null,
    @SerializedName(Constant.Employee.PHOTO_URL_SMALL)
    var photoUrlSmall: String? = null

)

