package com.animlistingdemo.data


interface EmployeeList {
    val employeeList: List<Employee?>
}

interface Employee {
    val uuid: String?
    val fullName: String?
    val email: String?
    val jobTitle: String?
    val thumbNail: String?
    val team: String?
}