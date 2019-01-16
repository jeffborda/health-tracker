package com.example.health_tracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class ExerciseDiary extends AppCompatActivity {

    // For Database
    private static final String DATABASE_NAME = "exercise-database";
    private ExerciseDatabase exerciseDatabase;
    // For RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);


        exerciseDatabase = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        //Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview#java
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ExerciseDiaryAdapter(exerciseDatabase.exerciseDao().getAll());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onExerciseSubmit(View v) {
        // Get the text from the EditText
        String exerciseTitle = ((EditText)findViewById(R.id.exercise_diary_title_input)).getText().toString();
        String exerciseDescription = ((EditText)findViewById(R.id.exercise_diary_description_input)).getText().toString();
        String exerciseQuantity = ((EditText)findViewById(R.id.exercise_diary_quantity_input)).getText().toString();
        // Create a new exercise
        Exercise newExercise = new Exercise(exerciseTitle, exerciseQuantity, exerciseDescription);
        // Store the exercise in the database
        exerciseDatabase.exerciseDao().insertExercise(newExercise);
    }




}
