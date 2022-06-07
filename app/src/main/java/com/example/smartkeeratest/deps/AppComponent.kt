package com.example.smartkeeratest.deps

import com.example.smartkeeratest.MyApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class, AppModule::class])
interface AppComponent {

    fun inject(app: MyApp)

}