package com.example.smartkeeratest.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartkeeratest.repositories.SsmaRepository

class SsmaViewModelFactory(private val repository: SsmaRepository, private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SsmaViewModel(repository, context) as T
    }

}