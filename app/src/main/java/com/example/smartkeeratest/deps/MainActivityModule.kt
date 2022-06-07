package com.example.smartkeeratest.deps

import com.example.smartkeeratest.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

}