package com.alexzh.mockdependenciesandroiduitests.screens.list.mapper

import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.screens.list.model.CoffeeDrinkUI

class CoffeeDrinkMapper {

    fun map(drinks: List<Drink>): List<CoffeeDrinkUI> {
        return drinks.map {
            map(it)
        }
    }

    private fun map(drink: Drink): CoffeeDrinkUI {
        return CoffeeDrinkUI(
            drink.id,
            drink.name
        )
    }
}