package com.pixelart.githubapiusers.data.repository

import android.util.Log
import com.pixelart.githubapiusers.data.network.NetworkResponse
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T: Any> apiCall(call: suspend() -> Response<T>, error: String ):T?{
        val result = apiOutput(call, error)
        var output : T? = null
        when(result){
            is NetworkResponse.Success ->
                output = result.output
            is NetworkResponse.Error -> Log.e("Error", "$error: ${result.exception}")
        }
        return output
    }

    private suspend fun<T : Any> apiOutput(call: suspend()-> Response<T> , error: String) : NetworkResponse<T>{
        val response = call.invoke()
        return if (response.isSuccessful)
            NetworkResponse.Success(response.body()!!)
        else
            NetworkResponse.Error(IOException("Data fetching failed due to: $error"))

    }
}