package com.example.smartkeeratest.deps

import android.content.Context
import com.example.smartkeeratest.api.RetrofitHelper
import com.example.smartkeeratest.api.SsmaApi
import com.example.smartkeeratest.repositories.SsmaRepository

class AppContainer(val context: Context) {
    val ssmaService = RetrofitHelper.getInstance().create(SsmaApi::class.java)
    val repository = SsmaRepository(ssmaService)

    var mainContainer: MainContainer? = null

}