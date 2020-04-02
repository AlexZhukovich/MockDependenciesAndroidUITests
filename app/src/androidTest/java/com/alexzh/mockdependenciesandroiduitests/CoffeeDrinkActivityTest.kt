package com.alexzh.mockdependenciesandroiduitests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.alexzh.mockdependenciesandroiduitests.RecyclerViewMatchers.withItemCount
import com.alexzh.mockdependenciesandroiduitests.data.DrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import com.alexzh.mockdependenciesandroiduitests.di.coffeeDrinksModule
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksActivity
import io.mockk.coEvery
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject
import java.net.UnknownHostException

class CoffeeDrinkActivityTest : KoinTest {

    @get:Rule
    val activityRule = ActivityTestRule(CoffeeDrinksActivity::class.java, true, false)

    private val repository: DrinksRepository by inject()
    private val service: CoffeeDrinksService by inject()

    @Before
    fun setUp() {
        loadKoinModules(modules = listOf(testDataModule, coffeeDrinksModule))
    }

    @Test
    fun shouldDisplayListOfCoffeesWith2Items() {
        val drinks = listOf(
            Drink(1L, "Coffee-1"),
            Drink(2L, "Coffee-2")
        )
        coEvery { repository.getCoffeeDrinks() } returns drinks

        activityRule.launchActivity(null)

        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(drinks.size)))
    }

    @Test
    fun shouldDisplayErrorMessageWithTryAgainButton() {
        coEvery { service.getCoffeeDrinks() } throws UnknownHostException("test")

        activityRule.launchActivity(null)

        onView(withId(R.id.errorMessage))
            .check(matches(withText(R.string.error_network_message)))

        onView(withId(R.id.tryAgain))
            .check(matches(withText(R.string.try_again_label)))
    }
}