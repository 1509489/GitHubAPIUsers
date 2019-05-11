package com.pixelart.githubapiusers.ui.usersscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pixelart.githubapiusers.data.dto.AllUsers
import com.pixelart.githubapiusers.data.repository.AllUsersRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AllUsersViewModel(private val repository: AllUsersRepository): ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext get() = job + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)
    private val alluserLiveData = MutableLiveData<MutableList<AllUsers>>()

    fun getAllUsers(){
        coroutineScope.launch {
            val users = repository.getAllUsers()
            alluserLiveData.postValue(users)
        }
    }

    fun getUsersLiveData() = alluserLiveData

    fun cancelRequest() = coroutineContext.cancel()
}