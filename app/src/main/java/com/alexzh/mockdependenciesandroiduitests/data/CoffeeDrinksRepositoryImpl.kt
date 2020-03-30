package com.alexzh.mockdependenciesandroiduitests.data

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService

class CoffeeDrinksRepositoryImpl(
    private val coffeeDrinksService: CoffeeDrinksService
) : DrinksRepository {

    override suspend fun getCoffeeDrinks(): List<Drink> {
        return coffeeDrinksService.getCoffeeDrinks()
    }
}