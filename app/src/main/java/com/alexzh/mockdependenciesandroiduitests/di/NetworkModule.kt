package com.alexzh.mockdependenciesandroiduitests.di

import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://raw.githubusercontent.com/AlexZhukovich/MockDependenciesAndroidUITests/master/json/"

    @Provides
    fun provideCoffeeDrinkService(): CoffeeDrinksService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeDrinksService::class.java)
    }
}