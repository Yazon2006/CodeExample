package com.example.myapplication

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class IPCheckViewModel @ViewModelInject constructor(
    private val ipCheckRepository: IpCheckRepository,
    private val ipAddressVerifier: IpAddressVerifier
) : ViewModel() {

    private val resultMutable = MutableLiveData<IpCheckResult>()
    val result: LiveData<IpCheckResult> = resultMutable

    init {
        viewModelScope.launch {
            ipCheckRepository.getLatestCached()?.let {
                resultMutable.postValue(IpCheckResult.Result(it))
            }
        }
    }

    fun check(address: String) {
        if (ipAddressVerifier.isValid(address)) {
            viewModelScope.launch {
                try {
                    val info = ipCheckRepository.getInfo(address)
                    resultMutable.postValue(IpCheckResult.Result(info))
                } catch (e: Exception) {
                    resultMutable.postValue(IpCheckResult.Error(e))
                }
            }
        } else {
            resultMutable.postValue(IpCheckResult.NotValid)
        }
    }

}