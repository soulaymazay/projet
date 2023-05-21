package com.example.transportenligne;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class loginActivity extends AppCompatActivity {
    ImageButton imageButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.printf("test10");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageButton = findViewById(R.id.flesh);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(loginActivity.this, MainActivity_accueil.class);
            startActivity(intent);
        });
    }
}
