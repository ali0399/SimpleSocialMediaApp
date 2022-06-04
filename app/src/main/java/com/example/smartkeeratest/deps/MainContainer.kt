package com.example.smartkeeratest.deps

import android.content.Context
import com.example.smartkeeratest.repositories.SsmaRepository
import com.example.smartkeeratest.viewModels.SsmaViewModelFactory1

class MainContainer(repository: SsmaRepository, context: Context) {

    val ssmaViewModelFactory1 = SsmaViewModelFactory1(repository, context)
}