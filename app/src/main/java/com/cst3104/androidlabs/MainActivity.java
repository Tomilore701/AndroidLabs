package com.cst3104.androidlabs;  // Ensure this is the correct package name for your project

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.cst3104.androidlabs.R;  // Import your R file for resources
import com.cst3104.androidlabs.SecondActivity;  // Import SecondActivity correctly

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w(TAG, "In onCreate() - Loading Widgets");

        // Retrieve saved email address from SharedPreferences
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String savedEmail = prefs.getString("LoginName", "");

        // Set the retrieved email to the EditText
        EditText emailEditText = findViewById(R.id.emailEditText);
        emailEditText.setText(savedEmail);

        // Set OnClickListener on login button to start SecondActivity
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();

            // Save the email address in SharedPreferences
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", email);
            editor.apply();

            // Create an Intent to transition to SecondActivity
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra("EmailAddress", email);
            startActivity(nextPage);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "In onStart() - App started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "In onResume() - App resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "In onPause() - App paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "In onStop() - App stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "In onDestroy() - App destroyed");
    }
}
