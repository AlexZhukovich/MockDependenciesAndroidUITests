package com.alexzh.mockdependenciesandroiduitests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.mockdependenciesandroiduitests.RecyclerViewMatchers.withItemCount
import com.alexzh.mockdependenciesandroiduitests.data.model.Drink
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksService
import com.alexzh.mockdependenciesandroiduitests.di.DaggerTestAppComponent
import com.alexzh.mockdependenciesandroiduitests.di.TestDataModule
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksActivity
import io.mockk.coEvery
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException
import javax.inject.Inject

class CoffeeDrinkActivityTest {

    @Inject
    lateinit var service: CoffeeDrinksService

    @Before
    fun setUp() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CoffeeDrinksApp
        val appComponent = DaggerTestAppComponent.builder()
            .dataModule(TestDataModule())
            .build()
        app.setAppComponent(appComponent)
        appComponent.inject(this)

        ActivityScenario.launch(CoffeeDrinksActivity::class.java)
    }

    @Test
    fun shouldDisplayListOfCoffeesWith2Items() {
        val drinks = listOf(
            Drink(1L, "Coffee-1"),
            Drink(2L, "Coffee-2")
        )
        coEvery { service.getCoffeeDrinks() } returns drinks

        ActivityScenario.launch(CoffeeDrinksActivity::class.java)

        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(drinks.size)))
    }

    @Test
    fun shouldDisplayNoDataAvailableErrorMessageWithTryAgainButton() {
        coEvery { service.getCoffeeDrinks() } returns emptyList()

        ActivityScenario.launch(CoffeeDrinksActivity::class.java)

        onView(withId(R.id.errorMessage))
            .check(matches(withText(R.string.error_network_message)))

        onView(withId(R.id.tryAgain))
            .check(matches(withText(R.string.try_again_label)))
    }

    @Test
    fun shouldDisplayUnknownErrorMessageWithTryAgainButton() {
        coEvery { service.getCoffeeDrinks() } throws SocketTimeoutException()

        ActivityScenario.launch(CoffeeDrinksActivity::class.java)

        onView(withId(R.id.errorMessage))
            .check(matches(withText(R.string.error_unknown_message)))

        onView(withId(R.id.tryAgain))
            .check(matches(withText(R.string.try_again_label)))
    }
}