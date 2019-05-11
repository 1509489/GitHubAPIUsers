package com.pixelart.githubapiusers.ui.profilescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pixelart.githubapiusers.data.dto.SingleUser
import com.pixelart.githubapiusers.data.repository.SingleUserRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(private val repository: SingleUserRepository):ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext get() = job + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)
    private val userLiveData = MutableLiveData<SingleUser>()

    fun getUser(user: String){
        coroutineScope.launch {
            userLiveData.postValue(repository.getUser(user))
        }
    }

    fun getUsersLiveData() = userLiveData
    fun cancelRequest() = coroutineContext.cancel()
}