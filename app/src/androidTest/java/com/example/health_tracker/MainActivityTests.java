package com.example.health_tracker;


import android.provider.ContactsContract;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    // Stopwatch Button Tests
    @Test
    public void stopwatchButtonIsEnabled() {
        onView(withId(R.id.stopwatch_button)).check(matches(isEnabled()));
    }

    @Test
    public void stopwatchButtonIsDisplayed() {
        onView(withId(R.id.stopwatch_button)).check(matches(isDisplayed()));
    }

    @Test
    public void stopwatchButtonIsCompletelyDisplayed() {
        onView(withId(R.id.stopwatch_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void stopwatchButtonIsNotSelectable() {
        onView(withId(R.id.stopwatch_button)).check(matches(not(isSelected())));
    }

    @Test
    public void stopwatchButtonIsClickable() {
        onView(withId(R.id.stopwatch_button)).check(matches(isClickable()));
    }

    @Test
    public void stopwatchButtonWithText() {
        onView(withId(R.id.stopwatch_button)).check(matches(withText(R.string.stopwatch_button)));
    }



    // Finger Exercise Button Tests
    @Test
    public void fingerExerciseButtonIsEnabled() {
        onView(withId(R.id.finger_exercise_button)).check(matches(isEnabled()));
    }

    @Test
    public void fingerExerciseButtonIsDisplayed() {
        onView(withId(R.id.finger_exercise_button)).check(matches(isDisplayed()));
    }

    @Test
    public void fingerExerciseButtonIsCompletelyDisplayed() {
        onView(withId(R.id.finger_exercise_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void fingerExerciseButtonIsNotSelectable() {
        onView(withId(R.id.finger_exercise_button)).check(matches(not(isSelected())));
    }

    @Test
    public void fingerExerciseButtonIsClickable() {
        onView(withId(R.id.finger_exercise_button)).check(matches(isClickable()));
    }

    @Test
    public void fingerExerciseButtonWithText() {
        onView(withId(R.id.finger_exercise_button)).check(matches(withText(R.string.finger_exercise_button)));
    }




    // Exercise Diary Button Tests
    @Test
    public void exerciseDiaryButtonIsEnabled() {
        onView(withId(R.id.exercise_diary_button)).check(matches(isEnabled()));
    }

    @Test
    public void exerciseDiaryButtonIsDisplayed() {
        onView(withId(R.id.exercise_diary_button)).check(matches(isDisplayed()));
    }

    @Test
    public void exerciseDiaryButtonIsCompletelyDisplayed() {
        onView(withId(R.id.exercise_diary_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void exerciseDiaryButtonIsNotSelectable() {
        onView(withId(R.id.exercise_diary_button)).check(matches(not(isSelected())));
    }

    @Test
    public void exerciseDiaryButtonIsClickable() {
        onView(withId(R.id.exercise_diary_button)).check(matches(isClickable()));
    }

    @Test
    public void exerciseDiaryButtonWithText() {
        onView(withId(R.id.exercise_diary_button)).check(matches(withText(R.string.exercise_button)));
    }




    // Send Water Reminders Button Tests
    @Test
    public void waterRemindersButtonIsEnabled() {
        onView(withId(R.id.send_notification_button)).check(matches(isEnabled()));
    }

    @Test
    public void waterRemindersButtonIsDisplayed() {
        onView(withId(R.id.send_notification_button)).check(matches(isDisplayed()));
    }

    @Test
    public void waterRemindersButtonIsCompletelyDisplayed() {
        onView(withId(R.id.send_notification_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void waterRemindersButtonIsNotSelectable() {
        onView(withId(R.id.send_notification_button)).check(matches(not(isSelected())));
    }

    @Test
    public void waterRemindersButtonIsClickable() {
        onView(withId(R.id.send_notification_button)).check(matches(isClickable()));
    }

    @Test
    public void waterRemindersButtonWithText() {
        onView(withId(R.id.send_notification_button)).check(matches(withText(R.string.water_notification_button)));
    }




}
