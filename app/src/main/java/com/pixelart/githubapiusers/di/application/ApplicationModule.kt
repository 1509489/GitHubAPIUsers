package com.pixelart.githubapiusers.di.application

import com.pixelart.githubapiusers.data.network.NetworkService
import com.pixelart.githubapiusers.data.repository.AllUsersRepository
import com.pixelart.githubapiusers.data.repository.SingleUserRepository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideAllUsersRepository(networkService: NetworkService):AllUsersRepository = AllUsersRepository(networkService)

    @Provides
    @ApplicationScope
    fun provideSingleUserRepository(networkService: NetworkService):SingleUserRepository = SingleUserRepository(networkService)
}