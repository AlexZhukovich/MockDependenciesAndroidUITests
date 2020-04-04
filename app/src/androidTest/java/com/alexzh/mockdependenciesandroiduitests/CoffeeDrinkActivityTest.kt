package com.alexzh.mockdependenciesandroiduitests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.alexzh.mockdependenciesandroiduitests.RecyclerViewMatchers.withItemCount
import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import com.alexzh.mockdependenciesandroiduitests.di.app.AppComponent
import com.alexzh.mockdependenciesandroiduitests.di.data.DataModule
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksActivity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import it.cosenonjaviste.daggermock.DaggerMock
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class CoffeeDrinkActivityTest {

    @get:Rule
    val daggerMockRule = DaggerMock.rule<AppComponent>(DataModule()) {
        set { appComponent ->
            val app = InstrumentationRegistry.getInstrumentation()
                .targetContext.applicationContext as CoffeeDrinksApp
            app.setAppComponent(appComponent)
        }
    }

    @get:Rule
    val activityRule = ActivityTestRule(CoffeeDrinksActivity::class.java, true, false)

    private val service: CoffeeDrinksService = mock()

    @Test
    fun shouldDisplayListOfCoffeesWith2Items() {
        val drinks = listOf(
            Drink(1L, "Coffee-1"),
            Drink(2L, "Coffee-2")
        )
        runBlocking {
            whenever(service.getCoffeeDrinks()).thenReturn(drinks)
        }
        activityRule.launchActivity(null)

        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(drinks.size)))
    }

    @Test
    fun shouldDisplayNoDataAvailableErrorMessageWithTryAgainButton() {
        runBlocking {
            whenever(service.getCoffeeDrinks()).thenReturn(emptyList())
        }
        activityRule.launchActivity(null)

        onView(withId(R.id.errorMessage))
            .check(matches(withText(R.string.error_network_message)))

        onView(withId(R.id.tryAgain))
            .check(matches(withText(R.string.try_again_label)))
    }

    @Test
    fun shouldDisplayUnknownErrorMessageWithTryAgainButton() {
        runBlocking {
            whenever(service.getCoffeeDrinks()).thenThrow(RuntimeException())
        }
        activityRule.launchActivity(null)

        onView(withId(R.id.errorMessage))
            .check(matches(withText(R.string.error_unknown_message)))

        onView(withId(R.id.tryAgain))
            .check(matches(withText(R.string.try_again_label)))
    }
}