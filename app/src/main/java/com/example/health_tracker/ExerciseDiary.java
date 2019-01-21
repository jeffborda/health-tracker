package com.example.health_tracker;

import android.Manifest;
import androidx.room.Room;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Location Services - RE: https://developer.android.com/training/location/retrieve-current#java

public class ExerciseDiary extends AppCompatActivity {

    // For Local Room Database
    private static final String DATABASE_NAME = "exercise-database";
    private ExerciseDatabase exerciseDatabase;
    // For RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    // List to hold local db and server db exercises
    private List<Exercise> exercises;
    // Location
    private FusedLocationProviderClient mFusedLocationClient;
    private Location lastLocation;
    private static final int ALL_PERMISSIONS_RESULT = 1011;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        getFromDataBase();


    }

    public void onExerciseSubmit(View v) {
        // Get the text from the EditText
        String exerciseTitle = ((EditText)findViewById(R.id.exercise_diary_title_input)).getText().toString();
        String exerciseDescription = ((EditText)findViewById(R.id.exercise_diary_description_input)).getText().toString();
        String exerciseQuantity = ((EditText)findViewById(R.id.exercise_diary_quantity_input)).getText().toString();
        // Create a new exercise
        Exercise newExercise = new Exercise(exerciseTitle, exerciseQuantity, exerciseDescription);
        // Store the exercise in the local database
        exerciseDatabase.exerciseDao().insertExercise(newExercise);
        // Store exercise in the Heroku database
        postExercise(exerciseTitle, exerciseQuantity, exerciseDescription);

        // Refresh the RecyclerView
        finish();
        startActivity(getIntent());


    }


    // RE: Android Documentation https://developer.android.com/training/volley/simple
    public void getFromDataBase() {
// Instantiate the RequestQueue
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
                        // TODO: Save in the DB with the fact that it didn't POST (create a new field)
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


    // RE: Android Documentation
    public void getLocation() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)  != PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                System.out.println("LOCATION~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println(location.getLatitude());
                                System.out.println(location.getLongitude());
                            }
                            else {
                                //TODO: What to do when location is unknown
                            }
                        }
                    });


            // Permission is not granted
            // Should we show an explanation? NO!!!! IT'S A TRAP!!!!!
        } else {
            // Permission has already been granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ALL_PERMISSIONS_RESULT);

                // ALL_PERMISSIONS_RESULT is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the task you need to do.
                    System.out.println("Got HERE");
                    getLocation();
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    // TODO: Add code to handle what to do if permission not given to use location
                    System.out.println("CANNOT GET PERMISSIONS TO GET LOCATION");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }







}
