package com.alexzh.mockdependenciesandroiduitests.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoffeeDrinksServiceFactory {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/AlexZhukovich/MockDependenciesAndroidUITests/master/json/"
    }

    fun create(): CoffeeDrinksService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeDrinksService::class.java)
    }
}