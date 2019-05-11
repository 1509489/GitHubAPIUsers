package com.pixelart.githubapiusers.di.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pixelart.githubapiusers.factories.AllUsersViewModelFactory
import com.pixelart.githubapiusers.factories.ProfileViewModelFactory
import com.pixelart.githubapiusers.ui.profilescreen.ProfileViewModel
import com.pixelart.githubapiusers.ui.usersscreen.AllUsersViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideAllUsersViewModel(factory: AllUsersViewModelFactory) =
        ViewModelProviders.of(fragment, factory).get(AllUsersViewModel::class.java)

    @Provides
    @FragmentScope
    fun provideProfileViewModel(factory: ProfileViewModelFactory) =
        ViewModelProviders.of(fragment, factory).get(ProfileViewModel::class.java)
}