package com.example.smartkeeratest.deps

import android.content.Context
import com.example.smartkeeratest.api.RetrofitHelper
import com.example.smartkeeratest.api.SsmaApi
import com.example.smartkeeratest.repositories.SsmaRepository
import com.example.smartkeeratest.viewModels.SsmaViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideSsmaApi(): SsmaApi {
        return RetrofitHelper.getInstance().create(SsmaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSsmaViewModel(ssmaRepository: SsmaRepository): SsmaViewModel {
        return SsmaViewModel(ssmaRepository, context)
    }


}