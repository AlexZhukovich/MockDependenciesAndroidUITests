package com.alexzh.mockdependenciesandroiduitests.di.app

import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksViewModelFactory
import com.alexzh.mockdependenciesandroiduitests.screens.list.mapper.CoffeeDrinkMapper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideCoffeeDrinkMapper(): CoffeeDrinkMapper {
        return CoffeeDrinkMapper()
    }

    @Provides
    fun provideCoffeeDrinksViewModelFactory(
        repository: DrinksRepository,
        mapper: CoffeeDrinkMapper
    ): CoffeeDrinksViewModelFactory {
        return CoffeeDrinksViewModelFactory(repository, mapper)
    }
}