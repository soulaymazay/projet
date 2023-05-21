package com.example.transportenligne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_iscritchauffeur extends AppCompatActivity {
    Button button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.printf("test4");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscritchauffeur);
        button4 = findViewById(R.id.infochauff);
        button5 = findViewById(R.id.infomt);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_iscritchauffeur.this, MainActivity_inscrit_infochauffeur.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_iscritchauffeur.this, MainActivity_inscritinfo_mt.class);
                startActivity(intent);
            }
        });

    }
}