package com.alexzh.mockdependenciesandroiduitests

import android.app.Application
import com.alexzh.mockdependenciesandroiduitests.di.app.AppComponent
import com.alexzh.mockdependenciesandroiduitests.di.app.AppModule
import com.alexzh.mockdependenciesandroiduitests.di.app.AppModule_ProvideCoffeeDrinkMapperFactory.create
import com.alexzh.mockdependenciesandroiduitests.di.app.DaggerAppComponent
import com.alexzh.mockdependenciesandroiduitests.di.app.DaggerAppComponent.create

class CoffeeDrinksApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent.builder()
             .appModule(AppModule())
             .build()
    }

    fun getAppComponent(): AppComponent = appComponent
}