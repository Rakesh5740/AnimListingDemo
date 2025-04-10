package com.animlistingdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animlistingdemo.data.AnimDetails
import com.animlistingdemo.data.AnimItem
import com.animlistingdemo.network.ApiState
import com.animlistingdemo.repository.AnimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AnimRepository
) : ViewModel() {

    private val _animList = MutableLiveData<List<AnimItem>>()
    val animList: LiveData<List<AnimItem>>
        get() = _animList

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean>
        get() = _isError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _detailsResponse = MutableLiveData<AnimDetails>()
    val detailsResponse: LiveData<AnimDetails>
        get() = _detailsResponse

    init {
        fetchHomeData()
    }

     fun fetchHomeData() {
        viewModelScope.launch {
            when (val list = repository.getAnimData()) {
                is ApiState.Loading -> {
                    _isLoading.value = true
                }

                is ApiState.Success -> {
                    val data = list.data
                    _animList.value = data
                }

                is ApiState.Error -> {
                    _isError.value = true
                    Timber.e("Something went wrong")
                }
            }
        }
    }

    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            when (val detailsData = repository.getAnimDetails(id)) {
                is ApiState.Loading -> {
                    _isLoading.value = true
                }

                is ApiState.Success -> {
                    val data = detailsData.data
                    _detailsResponse.value = data
                }

                is ApiState.Error -> {
                    _isError.value = true
                    Timber.e("Something went wrong")
                }
            }
        }
    }
}