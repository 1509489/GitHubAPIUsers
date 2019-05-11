package com.pixelart.githubapiusers.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pixelart.githubapiusers.data.repository.SingleUserRepository
import com.pixelart.githubapiusers.ui.profilescreen.ProfileViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory @Inject constructor(val repository: SingleUserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) ProfileViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel not found")
    }
}