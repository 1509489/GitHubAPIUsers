package com.pixelart.githubapiusers.di.fragment

import com.pixelart.githubapiusers.factories.AllUsersViewModelFactory
import com.pixelart.githubapiusers.ui.usersscreen.UsersFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun getAllUsersViewModelFactory(): AllUsersViewModelFactory

    fun injectUsersScreen(usersFragment: UsersFragment)
}