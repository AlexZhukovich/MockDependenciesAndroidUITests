package com.alexzh.mockdependenciesandroiduitests.di.app

import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import com.alexzh.mockdependenciesandroiduitests.di.data.DataModule
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, AppModule::class])
interface AppComponent {
    fun coffeeDrinksService(): CoffeeDrinksService

    fun inject(activity: CoffeeDrinksActivity)
}