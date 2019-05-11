package com.pixelart.githubapiusers.data.repository

import com.pixelart.githubapiusers.data.dto.SingleUser
import com.pixelart.githubapiusers.data.network.NetworkService

class SingleUserRepository(private val networkService: NetworkService):BaseRepository() {
    suspend fun getUser(user: String):SingleUser?{
        return apiCall(
            call = {networkService.getSingleUser(user).await()},
            error = "Failed to fetch user"
        )
    }
}