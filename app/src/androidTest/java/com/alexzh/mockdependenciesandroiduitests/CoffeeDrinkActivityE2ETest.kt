package com.alexzh.mockdependenciesandroiduitests

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.alexzh.mockdependenciesandroiduitests.RecyclerViewMatchers.withItemCount
import com.alexzh.mockdependenciesandroiduitests.screens.list.CoffeeDrinksActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CoffeeDrinkActivityE2ETest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityTestRule(CoffeeDrinksActivity::class.java)

    private val progressBarVisibility by lazy {
        ViewVisibilityIdlingResource(
            activityRule.activity,
            R.id.progressBar,
            View.GONE
        )
    }

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingPolicies.setIdlingResourceTimeout(1, TimeUnit.SECONDS)
        IdlingRegistry.getInstance().register(progressBarVisibility)
    }

    @Test
    fun shouldDisplayListOfCoffeesWith10Items() {
        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(10)))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(progressBarVisibility)
    }
}