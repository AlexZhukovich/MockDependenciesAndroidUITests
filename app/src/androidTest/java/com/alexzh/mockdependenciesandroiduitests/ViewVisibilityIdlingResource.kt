package com.alexzh.mockdependenciesandroiduitests

import android.app.Activity
import android.view.View
import androidx.test.espresso.IdlingResource

class ViewVisibilityIdlingResource(
    private val activity: Activity,
    private val viewId: Int,
    private val expectedVisibility: Int
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return ViewVisibilityIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        val view: View? = activity.findViewById(viewId)
        val isIdleNow = if (view != null) {
            view.visibility == expectedVisibility
        } else {
            false
        }

        if (isIdleNow && resourceCallback != null) {
            resourceCallback?.onTransitionToIdle()
        }
        return isIdleNow
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}