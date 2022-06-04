package com.example.smartkeeratest.viewModels

import android.content.Context
import com.example.smartkeeratest.repositories.SsmaRepository

class SsmaViewModelFactory1(private val repository: SsmaRepository, private val context: Context) :
    Factory<SsmaViewModel> {
    override fun create(): SsmaViewModel {
        return SsmaViewModel(repository, context)
    }

}