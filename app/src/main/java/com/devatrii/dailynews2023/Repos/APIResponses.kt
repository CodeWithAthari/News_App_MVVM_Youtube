package com.devatrii.dailynews2023.Repos

sealed class APIResponses<T>(var data: T? = null, var errorMessage: String? = null) {
    class Loading<T>() : APIResponses<T>()
    class Success<T>(private val mData: T) : APIResponses<T>(data = mData)
    class Error<T>(private val error: String) : APIResponses<T>(errorMessage = error)
}