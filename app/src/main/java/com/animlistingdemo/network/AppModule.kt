package com.animlistingdemo.network

import com.animlistingdemo.repository.AnimRepository
import com.animlistingdemo.repository.AnimRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindEmployeeRepository(
        employeeRepositoryImpl: AnimRepositoryImpl
    ): AnimRepository
}