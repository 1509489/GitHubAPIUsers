package com.pixelart.githubapiusers.data.network

import com.pixelart.githubapiusers.data.dto.AllUsers
import com.pixelart.githubapiusers.data.dto.SingleUser
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("users")
    fun getAllUsers():Deferred<List<AllUsers>>

    @GET("users/{user}")
    fun getSingleUser(@Path("user") user: String):Deferred<SingleUser>
}