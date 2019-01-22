package com.example.health_tracker;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExerciseDiaryTests {

    @Rule
    public ActivityTestRule<ExerciseDiary> activityRule = new ActivityTestRule<>(ExerciseDiary.class);

    // Finger Exercise Button Tests
    @Test
    public void fingerExerciseButtonIsEnabled() {
        onView(withId(R.id.add_exercise_button)).check(matches(isEnabled()));
    }

    @Test
    public void fingerExerciseButtonIsDisplayed() {
        onView(withId(R.id.add_exercise_button)).check(matches(isDisplayed()));
    }

    @Test
    public void fingerExerciseButtonIsCompletelyDisplayed() {
        onView(withId(R.id.add_exercise_button)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void fingerExerciseButtonIsNotSelectable() {
        onView(withId(R.id.add_exercise_button)).check(matches(not(isSelected())));
    }

    @Test
    public void fingerExerciseButtonIsClickable() {
        onView(withId(R.id.add_exercise_button)).check(matches(isClickable()));
    }

    @Test
    public void fingerExerciseButtonWithText() {
        onView(withId(R.id.add_exercise_button)).check(matches(withText(R.string.exercise_diary_submit_button)));
    }


}
