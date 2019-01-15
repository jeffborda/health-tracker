package com.example.health_tracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExerciseDiary extends AppCompatActivity {



    private static final String DATABASE_NAME = "exercise-database";
    private ExerciseDatabase exerciseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);


        exerciseDatabase = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        // Exercise(String title, int quantity, String description, String timestamp)
//        if(exerciseDB.exerciseDao().getAll().isEmpty()) {
//            Exercise exercise = new Exercise("Test Exercise", 100, "Insert into database push-ups", "10:00 am ...");
//            exerciseDB.exerciseDao().insertExercise(exercise);
//        }


    }




}
