package com.alexzh.mockdependenciesandroiduitests.di.data

import com.alexzh.mockdependenciesandroiduitests.data.CoffeeDrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataModule {

    @Provides
    @Singleton
    open fun provideCoffeeDrinksService(): CoffeeDrinksService {
        return CoffeeDrinksServiceFactory().create()
    }

    @Provides
    @Singleton
    open fun provideCoffeeDrinksRepository(service: CoffeeDrinksService): DrinksRepository {
        return CoffeeDrinksRepository(service)
    }
}