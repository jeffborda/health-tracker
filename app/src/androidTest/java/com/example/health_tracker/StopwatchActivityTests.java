package com.example.health_tracker;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StopwatchActivityTests {

    @Rule
    public ActivityTestRule<StopWatch> activityRule = new ActivityTestRule<>(StopWatch.class);



    // Stopwatch START Button Tests
    @Test
    public void startButtonIsEnabled() {
        onView(withId(R.id.start_button)).check(matches(isEnabled()));
    }

    @Test
    public void startButtonIsDisplayed() {
        onView(withId(R.id.start_button)).check(matches(isDisplayed()));
    }

    @Test
    public void startButtonIsCompletelyDisplayed() {
        onView(withId(R.id.start_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void startButtonIsNotSelectable() {
        onView(withId(R.id.start_button)).check(matches(not(isSelected())));
    }

    @Test
    public void startButtonIsClickable() {
        onView(withId(R.id.start_button)).check(matches(isClickable()));
    }

    @Test
    public void startButtonWithText() {
        onView(withId(R.id.start_button)).check(matches(withText(R.string.timer_button_start)));
    }



    // Stopwatch STOP Button Tests
    @Test
    public void stopButtonIsEnabled() {
        onView(withId(R.id.stop_button)).check(matches(isEnabled()));
    }

    @Test
    public void stopButtonIsDisplayed() {
        onView(withId(R.id.stop_button)).check(matches(isDisplayed()));
    }

    @Test
    public void stopButtonIsCompletelyDisplayed() {
        onView(withId(R.id.stop_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void stopButtonIsNotSelectable() {
        onView(withId(R.id.stop_button)).check(matches(not(isSelected())));
    }

    @Test
    public void stopButtonIsClickable() {
        onView(withId(R.id.stop_button)).check(matches(isClickable()));
    }

    @Test
    public void stopButtonWithText() {
        onView(withId(R.id.stop_button)).check(matches(withText(R.string.timer_button_stop)));
    }



    // Stopwatch RESET Button Tests
    @Test
    public void resetButtonIsEnabled() {
        onView(withId(R.id.reset_button)).check(matches(isEnabled()));
    }

    @Test
    public void resetButtonIsDisplayed() {
        onView(withId(R.id.reset_button)).check(matches(isDisplayed()));
    }

    @Test
    public void resetButtonIsCompletelyDisplayed() {
        onView(withId(R.id.reset_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void resetButtonIsNotSelectable() {
        onView(withId(R.id.reset_button)).check(matches(not(isSelected())));
    }

    @Test
    public void resetButtonIsClickable() {
        onView(withId(R.id.reset_button)).check(matches(isClickable()));
    }

    @Test
    public void resetButtonWithText() {
        onView(withId(R.id.reset_button)).check(matches(withText(R.string.timer_button_reset)));
    }


    @Test
    public void checkInitialStopwatchText() {
        onView(withId(R.id.stopwatch_text)).check(matches(withText(R.string.timer_zeroed_out_display)));
    }

    @Test
    public void checkRunningStopwatchText() {
        // Make sure the timer isn't still displaying the zeroed_out_display after user clicks START
        onView(withId(R.id.start_button)).perform(click());
        onView(withId(R.id.stopwatch_text)).check(matches(not(withText(R.string.timer_zeroed_out_display))));
    }

}
