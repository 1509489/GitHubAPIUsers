package com.pixelart.githubapiusers.data.network

import java.lang.Exception

sealed class NetworkResponse<out T: Any> {
    data class Success<out T: Any>(val output: T): NetworkResponse<T>()
    data class Error(val exception: Exception): NetworkResponse<Nothing>()
}