package com.example.health_tracker;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Reference: https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

@Database(version=1, entities = {Exercise.class})
public abstract class ExerciseDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
}
