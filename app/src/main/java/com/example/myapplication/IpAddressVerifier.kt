package com.example.myapplication

import android.util.Patterns
import javax.inject.Inject

class IpAddressVerifier @Inject constructor() {
    fun isValid(address: String): Boolean {
        return Patterns.IP_ADDRESS.matcher(address).matches()
    }
}