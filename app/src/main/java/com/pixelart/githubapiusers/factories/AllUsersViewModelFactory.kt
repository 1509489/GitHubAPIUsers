package com.pixelart.githubapiusers.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pixelart.githubapiusers.data.repository.AllUsersRepository
import com.pixelart.githubapiusers.ui.usersscreen.AllUsersViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AllUsersViewModelFactory @Inject constructor(val repository: AllUsersRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllUsersViewModel::class.java)) AllUsersViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel not found")
    }
}