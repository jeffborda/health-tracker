package com.example.health_tracker;

import android.arch.persistence.room.Database;

@Database(version=1, entities = {Exercise.class})
public abstract class ExerciseDatabase {
}
