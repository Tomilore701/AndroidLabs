package com.cst3104.androidlabs;  // Ensure this is the correct package name

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.cst3104.androidlabs.R;  // Ensure your R file is correctly imported

public class SecondActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get the email address passed from MainActivity
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        // Display the welcome message with the email
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome back " + emailAddress);

        // Retrieve saved phone number from SharedPreferences
        prefs = getSharedPreferences("MyData", MODE_PRIVATE);
        String savedPhoneNumber = prefs.getString("PhoneNumber", "");

        EditText phoneEditText = findViewById(R.id.phoneEditText);
        phoneEditText.setText(savedPhoneNumber);

        // Handle the "Call number" button click
        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(v -> {
            String phoneNumber = phoneEditText.getText().toString();

            // Create an Intent to make a phone call
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(android.net.Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save phone number when the activity goes off screen
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        String phoneNumber = phoneEditText.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PhoneNumber", phoneNumber);
        editor.apply();
    }
}
