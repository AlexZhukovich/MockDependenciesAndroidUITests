package com.alexzh.mockdependenciesandroiduitests.data

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService

class CoffeeDrinksRepository(
    private val service: CoffeeDrinksService
) : DrinksRepository {

    override suspend fun getCoffeeDrinks(): List<Drink> {
        return service.getCoffeeDrinks()
    }
}