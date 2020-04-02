package com.alexzh.mockdependenciesandroiduitests.di

import com.alexzh.mockdependenciesandroiduitests.CoffeeDrinkActivityTest
import com.alexzh.mockdependenciesandroiduitests.di.app.AppComponent
import com.alexzh.mockdependenciesandroiduitests.di.app.AppModule
import com.alexzh.mockdependenciesandroiduitests.di.data.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, AppModule::class])
interface TestAppComponent : AppComponent {
    fun inject(testSuite: CoffeeDrinkActivityTest)
}