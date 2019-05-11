package com.pixelart.githubapiusers.di.fragment

import com.pixelart.githubapiusers.factories.AllUsersViewModelFactory
import com.pixelart.githubapiusers.ui.profilescreen.ProfileFragment
import com.pixelart.githubapiusers.ui.profilescreen.ProfileViewModel
import com.pixelart.githubapiusers.ui.usersscreen.UsersFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun getAllUsersViewModelFactory(): AllUsersViewModelFactory
    fun getProfileViewModel():ProfileViewModel

    fun injectUsersScreen(usersFragment: UsersFragment)
    fun injectProfileScreen(profileFragment: ProfileFragment)
}