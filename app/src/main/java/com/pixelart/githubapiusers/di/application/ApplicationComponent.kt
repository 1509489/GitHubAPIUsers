package com.pixelart.githubapiusers.di.application

import com.pixelart.githubapiusers.di.fragment.FragmentComponent
import com.pixelart.githubapiusers.di.fragment.FragmentModule
import com.pixelart.githubapiusers.di.network.NetworkModule
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun newFragmentComponent(fragmentModule: FragmentModule):FragmentComponent
}