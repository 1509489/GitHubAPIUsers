package com.pixelart.githubapiusers.data.repository

import com.pixelart.githubapiusers.data.dto.AllUsers
import com.pixelart.githubapiusers.data.network.NetworkService

class AllUsersRepository(private val networkService: NetworkService): BaseRepository() {

    suspend fun getAllUsers():MutableList<AllUsers>?{
        return apiCall(
            call = {networkService.getAllUsers().await()},
            error = "Failed to fetch users"
        )?.toMutableList()
    }
}