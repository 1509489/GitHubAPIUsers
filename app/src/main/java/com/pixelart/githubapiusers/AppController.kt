package com.pixelart.githubapiusers

import android.app.Application
import com.pixelart.githubapiusers.di.application.ApplicationComponent
import com.pixelart.githubapiusers.di.application.ApplicationModule
import com.pixelart.githubapiusers.di.application.DaggerApplicationComponent
import com.pixelart.githubapiusers.di.network.NetworkModule

class AppController:Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule())
            .networkModule(NetworkModule())
            .build()
    }
}