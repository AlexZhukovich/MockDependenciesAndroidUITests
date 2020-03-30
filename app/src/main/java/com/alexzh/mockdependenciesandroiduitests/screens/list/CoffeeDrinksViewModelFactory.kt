package com.alexzh.mockdependenciesandroiduitests.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.screens.list.mapper.CoffeeDrinkMapper

class CoffeeDrinksViewModelFactory(
    private val repository: DrinksRepository,
    private val mapper: CoffeeDrinkMapper
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            DrinksRepository::class.java,
            CoffeeDrinkMapper::class.java
        ).newInstance(repository, mapper)
    }
}