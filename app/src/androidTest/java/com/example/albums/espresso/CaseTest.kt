package com.example.albums.espresso

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.example.albums.R
import com.example.albums.presentation.MainActivity
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CaseTest {
    private lateinit var context: Context

    @get : Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun a_test_assert_current_activity_is_main_activity() {
        val activity = getActivity()
        assertTrue(activity::class == MainActivity::class)
    }

    @Test
    fun b_test_assert_activity_started_true() {
        assertTrue(rule.scenario.state == Lifecycle.State.RESUMED)
    }

    @Test
    fun c_test_assert_exit_button_is_displayed() {
        onView(withId(R.id.home_fragment_exit_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun d_test_assert_recycler_is_displayed() {
        onView(withId(R.id.home_fragment_recycler_view)).check(matches(isDisplayed()))
    }

    private fun getActivity(): Activity {
        val currentActivity = arrayOfNulls<Activity>(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val allActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED)
            if (!allActivities.isEmpty()) {
                currentActivity[0] = allActivities.iterator().next()
            }
        }
        return currentActivity[0]!!
    }

    @After
    fun cleanup() {
        rule.scenario.close()
    }
}