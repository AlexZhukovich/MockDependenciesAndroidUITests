package com.alexzh.mockdependenciesandroiduitests.data

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import kotlinx.coroutines.delay

class CoffeeDrinksRepository(
    private val service: CoffeeDrinksService
) : DrinksRepository {

    override suspend fun getCoffeeDrinks(): List<Drink> {
        // Added for demo purpose
        delay(1_000)

        return service.getCoffeeDrinks()
    }
}