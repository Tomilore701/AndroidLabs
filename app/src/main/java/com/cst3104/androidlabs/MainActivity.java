package com.cst3104.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button clickButton = findViewById(R.id.clickButton);
        CheckBox checkBox = findViewById(R.id.checkBox);
        ImageButton flagButton = findViewById(R.id.flagButton);
        Switch switchButton = findViewById(R.id.switchButton);
        EditText editText = findViewById(R.id.editText);


        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        getResources().getString(R.string.toast_message),
                        Toast.LENGTH_LONG).show();
            }
        });


        switchButton.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            // Create a message for the Snackbar
            String message = isChecked ? "Switch is now on" : "Switch is now off";


            Snackbar.make(compoundButton, message, Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Revert the switch state
                            switchButton.setChecked(!isChecked);
                        }
                    }).show();
        });
    }
}
