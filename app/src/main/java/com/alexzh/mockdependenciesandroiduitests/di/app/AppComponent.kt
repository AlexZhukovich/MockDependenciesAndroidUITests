package com.alexzh.mockdependenciesandroiduitests.di.app

import com.alexzh.mockdependenciesandroiduitests.di.data.DataModule
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksActivity
import dagger.Component

@Component(modules = [DataModule::class, AppModule::class])
interface AppComponent {
    fun inject(activity: CoffeeDrinksActivity)
}