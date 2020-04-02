package com.alexzh.mockdependenciesandroiduitests

import com.alexzh.mockdependenciesandroiduitests.data.CoffeeDrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import io.mockk.mockk
import org.koin.dsl.module

val testDataModule = module {
    single<CoffeeDrinksService>(override = true) { mockk() }
    single<DrinksRepository>(override = true) { CoffeeDrinksRepository(service = get()) }
}
