package com.architecture.clean.di.module

import com.examples.data.repository.AppRepoImp
import com.examples.data.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAppRepository(
        appRepoImp: AppRepoImp
    ): AppRepository
}