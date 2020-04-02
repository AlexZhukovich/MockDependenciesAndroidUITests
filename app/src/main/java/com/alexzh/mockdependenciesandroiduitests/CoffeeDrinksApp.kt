package com.alexzh.mockdependenciesandroiduitests

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.alexzh.mockdependenciesandroiduitests.di.app.AppComponent
import com.alexzh.mockdependenciesandroiduitests.di.app.AppModule
import com.alexzh.mockdependenciesandroiduitests.di.app.DaggerAppComponent

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

    @VisibleForTesting
    fun setAppComponent(component: AppComponent) {
        this.appComponent = component
    }
}