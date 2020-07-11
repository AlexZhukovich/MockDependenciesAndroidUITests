package com.alexzh.mockdependenciesandroiduitests.data

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import java.net.UnknownHostException
import javax.inject.Inject

class CoffeeDrinksRepository @Inject constructor(
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