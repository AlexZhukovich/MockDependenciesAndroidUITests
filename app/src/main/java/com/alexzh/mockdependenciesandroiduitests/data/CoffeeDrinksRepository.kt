package com.alexzh.mockdependenciesandroiduitests.data

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import java.net.UnknownHostException

class CoffeeDrinksRepository(
    private val service: CoffeeDrinksService
) : DrinksRepository {

    override suspend fun getCoffeeDrinks(): List<Drink> {
        return try {
            service.getCoffeeDrinks()
        } catch (ex: UnknownHostException) {
            emptyList()
        }
    }
}