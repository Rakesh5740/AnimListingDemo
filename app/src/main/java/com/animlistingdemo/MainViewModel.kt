package com.animlistingdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animlistingdemo.data.EmployeeList
import com.animlistingdemo.network.ApiState
import com.animlistingdemo.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val _employeeList = MutableLiveData<EmployeeList>()
    val employeeList: LiveData<EmployeeList>
        get() = _employeeList

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean>
        get() = _isError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        fetchDetails()
    }

    fun fetchDetails() {
        viewModelScope.launch {
            when (val list = repository.getEmployeeDetails()) {
                is ApiState.Loading -> {
                    _isLoading.value = true
                }

                is ApiState.Success -> {
                    val data = list.data
                    _employeeList.value = data
                }

                is ApiState.Error -> {
                    _isError.value = true
                    Timber.e("Something went wrong")
                }
            }
        }
    }
}