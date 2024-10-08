package com.cst3104.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        // Retrieving the email address passed from MainActivity
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        // Updating the TextView to display the welcome message with the email
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome back " + emailAddress);

        // Setting up the Call button functionality
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        Button callButton = findViewById(R.id.callButton);

        callButton.setOnClickListener(v -> {
            String phoneNumber = phoneEditText.getText().toString();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        Log.w(TAG, "In onCreate() - SecondActivity initialized");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "In onStart() - SecondActivity becoming visible");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "In onResume() - SecondActivity interacting with user");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "In onPause() - SecondActivity losing focus");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "In onStop() - SecondActivity no longer visible");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "In onDestroy() - SecondActivity being destroyed");
    }
}
