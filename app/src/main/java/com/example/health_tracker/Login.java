package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setTextUsername();
    }

    public void saveUsername(View v) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        EditText username = (EditText) findViewById(R.id.edit_username);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save the username to sharedPref's
        editor.putString("username", username.getText().toString());
        editor.commit();
        // Display the username in the TextView
        TextView usernameTextView = findViewById(R.id.display_username);
        String usernameStr =  sharedPreferences.getString("username", "nothing yet");
        usernameTextView.setText(usernameStr);
    }

//    public String getUsername() {
//
//    }
//
//    public void setTextUsername() {
//
//    }


}
