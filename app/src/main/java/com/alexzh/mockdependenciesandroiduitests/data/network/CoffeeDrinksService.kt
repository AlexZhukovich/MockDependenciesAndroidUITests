package com.alexzh.mockdependenciesandroiduitests.data.network

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import retrofit2.http.GET
import java.net.UnknownHostException

interface CoffeeDrinksService {

    @Throws(UnknownHostException::class)
    @GET("coffee-drinks.json")
    suspend fun getCoffeeDrinks(): List<Drink>
}