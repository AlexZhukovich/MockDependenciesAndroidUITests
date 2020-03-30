package com.alexzh.mockdependenciesandroiduitests.data.network

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import retrofit2.http.GET

interface CoffeeDrinksService {

    @GET("coffee-drinks.json")
    suspend fun getCoffeeDrinks(): List<Drink>
}