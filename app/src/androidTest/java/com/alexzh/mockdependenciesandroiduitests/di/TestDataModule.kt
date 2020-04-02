package com.alexzh.mockdependenciesandroiduitests.di

import com.alexzh.mockdependenciesandroiduitests.data.CoffeeDrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import com.alexzh.mockdependenciesandroiduitests.di.data.DataModule
import io.mockk.mockk

class TestDataModule : DataModule() {

    override fun provideCoffeeDrinksRepository(service: CoffeeDrinksService): DrinksRepository {
        return CoffeeDrinksRepository(service)
    }

    override fun provideCoffeeDrinksService(): CoffeeDrinksService {
        return mockk()
    }
}