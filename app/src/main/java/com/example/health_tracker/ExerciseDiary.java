package com.example.health_tracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ExerciseDiary extends AppCompatActivity {

    // For Database
    private static final String DATABASE_NAME = "exercise-database";
    private ExerciseDatabase exerciseDatabase;
    // For RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        getFromDataBase();

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


        postExercise(exerciseTitle, exerciseQuantity, exerciseDescription);




        // To refresh database
        finish();
        startActivity(getIntent());
    }


    // RE: Android Documentation https://developer.android.com/training/volley/simple
    public void getFromDataBase() {

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://healthtrackerbackend.herokuapp.com/exercise";

// Request a string response from the provided URL.
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //RE: https://stackoverflow.com/questions/8371274/how-to-parse-json-array-with-gson/8371455
                                Gson gson = new Gson();
                                Type listType = new TypeToken<ArrayList<Exercise>>(){}.getType();
                                exercises = gson.fromJson(response, listType);
                                // Add local database entries to the list
                                renderRecycler();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("ExerciseDiary", error.toString());
                    }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void renderRecycler() {


        exerciseDatabase = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
                .build();

        // Adds the data from the local database to exercises list
        exercises.addAll(exerciseDatabase.exerciseDao().getAll());



        //Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview#java
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ExerciseDiaryAdapter(exercises);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void postExercise(final String exerciseTitle, final String exerciseQuantity, final String exerciseDescription) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://healthtrackerbackend.herokuapp.com/exercise";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", exerciseTitle);
                params.put("quantity", exerciseQuantity);
                params.put("description", exerciseDescription);

                return params;
            }
        };
        queue.add(postRequest);
    }


}
