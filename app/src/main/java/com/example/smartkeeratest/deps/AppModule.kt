package com.example.smartkeeratest.deps

import com.example.smartkeeratest.api.RetrofitHelper
import com.example.smartkeeratest.api.SsmaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSsmaViewModel(): SsmaApi {
        return RetrofitHelper.getInstance().create(SsmaApi::class.java)

    }
}