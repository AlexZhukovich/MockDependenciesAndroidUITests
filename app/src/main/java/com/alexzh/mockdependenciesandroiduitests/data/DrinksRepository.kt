package com.alexzh.mockdependenciesandroiduitests.data

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink

interface DrinksRepository {

    suspend fun getCoffeeDrinks(): List<Drink>
}