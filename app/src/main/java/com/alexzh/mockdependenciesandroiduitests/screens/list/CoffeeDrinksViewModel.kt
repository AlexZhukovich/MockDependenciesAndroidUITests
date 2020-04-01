package com.alexzh.mockdependenciesandroiduitests.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.screens.common.UiState
import com.alexzh.mockdependenciesandroiduitests.screens.list.mapper.CoffeeDrinkMapper
import com.alexzh.mockdependenciesandroiduitests.screens.list.model.CoffeeDrinkUI
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class CoffeeDrinksViewModel(
    private val repository: DrinksRepository,
    private val mapper: CoffeeDrinkMapper
): ViewModel() {

    private val coffeeDrinks = MutableLiveData<UiState<List<CoffeeDrinkUI>>>()
    fun getCoffeeDrinks(): LiveData<UiState<List<CoffeeDrinkUI>>> = coffeeDrinks

    fun loadDrinks() {
        coffeeDrinks.value = UiState.Loading

        viewModelScope.launch {
            try {
                coffeeDrinks.value = UiState.Success(mapper.map(repository.getCoffeeDrinks()))
            } catch (ex: UnknownHostException) {
                coffeeDrinks.value = UiState.Error(ex)
            }
        }
    }
}