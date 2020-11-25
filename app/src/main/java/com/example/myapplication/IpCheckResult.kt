package com.example.myapplication

sealed class IpCheckResult {
    object NotValid : IpCheckResult()
    class Error(val exception: Exception) : IpCheckResult()
    class Result(val model: ResultUIModel) : IpCheckResult()
}