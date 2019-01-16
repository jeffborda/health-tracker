package com.example.health_tracker;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="exercise")
public class Exercise {
    @PrimaryKey(autoGenerate=true)
    public long id;

    public String title;
    public String quantity;
    public String description;
    public String timestamp;

    // Default Constructor
    public Exercise() {}

    public Exercise(String title, String quantity, String description) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = new Date().toString();
    }
}
