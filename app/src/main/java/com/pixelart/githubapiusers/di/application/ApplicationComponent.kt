package com.pixelart.githubapiusers.di.application

import com.pixelart.githubapiusers.di.network.NetworkModule
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
}