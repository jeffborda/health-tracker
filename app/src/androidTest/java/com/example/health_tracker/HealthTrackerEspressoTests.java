package com.example.health_tracker;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HealthTrackerEspressoTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void buttonOnMainActivityTest() {
        onView(withId(R.id.stopwatch_button)).check(matches(withText(containsString("Stopwatch"))));
        onView(withId(R.id.finger_exercise_button)).check(matches(withText(containsString("Finger Exercise"))));
        onView(withId(R.id.exercise_diary_button)).check(matches(withText(containsString("Exercise Diary"))));
        onView(withId(R.id.send_notification_button)).check(matches(withText(containsString("Send Water Reminders"))));
    }
    

}
