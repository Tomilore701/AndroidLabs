package com.cst3104.androidlabs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMessages;
    private EditText editTextMessage;
    private Button buttonSend, buttonReceive;
    private ArrayList<Message> messageList;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMessages = findViewById(R.id.listViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        buttonReceive = findViewById(R.id.buttonReceive);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, messageList);
        listViewMessages.setAdapter(chatAdapter);

        // Send button functionality
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                if (!message.isEmpty()) {
                    messageList.add(new Message(message, true)); // Sent message
                    chatAdapter.notifyDataSetChanged();
                    editTextMessage.setText("");
                }
            }
        });

        // Receive button functionality
        buttonReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                if (!message.isEmpty()) {
                    messageList.add(new Message(message, false)); // Received message
                    chatAdapter.notifyDataSetChanged();
                    editTextMessage.setText("");
                }
            }
        });

        // Long click to delete an item
        listViewMessages.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Do you want to delete this?");
            builder.setMessage("The selected row is: " + position);
            builder.setPositiveButton("Yes", (dialog, which) -> {
                messageList.remove(position);
                chatAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("No", null);
            builder.show();
            return true;
        });
    }
}
