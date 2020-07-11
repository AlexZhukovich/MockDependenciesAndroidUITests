package com.alexzh.mockdependenciesandroiduitests.screens.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.mockdependenciesandroiduitests.data.CoffeeDrinksRepository
import com.alexzh.mockdependenciesandroiduitests.screens.common.UiState
import com.alexzh.mockdependenciesandroiduitests.screens.list.exception.NoDataAvailableException
import com.alexzh.mockdependenciesandroiduitests.screens.list.mapper.CoffeeDrinkMapper
import com.alexzh.mockdependenciesandroiduitests.screens.list.model.CoffeeDrinkUI
import kotlinx.coroutines.launch

class CoffeeDrinksViewModel @ViewModelInject constructor(
    private val repository: CoffeeDrinksRepository,
    private val mapper: CoffeeDrinkMapper
): ViewModel() {

    private val coffeeDrinks = MutableLiveData<UiState<List<CoffeeDrinkUI>>>()
    fun getCoffeeDrinks(): LiveData<UiState<List<CoffeeDrinkUI>>> = coffeeDrinks

    fun loadDrinks() {
        coffeeDrinks.value = UiState.Loading

        viewModelScope.launch {
            try {
                val drinks = repository.getCoffeeDrinks()
                if (drinks.isNotEmpty()) {
                    coffeeDrinks.value = UiState.Success(mapper.map(drinks))
                } else {
                    coffeeDrinks.value = UiState.Error(NoDataAvailableException())
                }
            } catch (ex: Exception) {
                coffeeDrinks.value = UiState.Error(ex)
            }
        }
    }
}