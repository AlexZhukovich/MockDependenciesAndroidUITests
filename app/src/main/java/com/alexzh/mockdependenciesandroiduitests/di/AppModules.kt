package com.alexzh.mockdependenciesandroiduitests.di

import com.alexzh.mockdependenciesandroiduitests.data.CoffeeDrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksServiceFactory
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksViewModel
import com.alexzh.mockdependenciesandroiduitests.screens.list.mapper.CoffeeDrinkMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    factory { CoffeeDrinksServiceFactory().create() }
    factory<DrinksRepository> { CoffeeDrinksRepository(service = get()) }
}

val coffeeDrinksModule = module {
    factory { CoffeeDrinkMapper() }
    viewModel { CoffeeDrinksViewModel(repository = get(), mapper = get()) }
}