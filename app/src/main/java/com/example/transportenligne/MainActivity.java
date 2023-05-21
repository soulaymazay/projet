package com.example.transportenligne;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int duree=6000;
    ImageView mt,lettre_t,lettre_a,lettre_d,lettre_e,lettre_l,lettre_i;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation);

        mt=findViewById(R.id.mt);
        lettre_t=findViewById(R.id.lettre_t);
        lettre_a=findViewById(R.id.lettre_a);
        lettre_d=findViewById(R.id.lettre_d);
        lettre_e=findViewById(R.id.lettre_e);
        lettre_l=findViewById(R.id.lettre_l);

        lettre_i=findViewById(R.id.lettre_i);
        textView=findViewById(R.id.tran);

        lettre_t.animate().translationY(-500).setDuration(2000).setStartDelay(0);
        lettre_a.animate().translationY(-500).setDuration(2000).setStartDelay(500);
        lettre_d.animate().translationY(-500).setDuration(2000).setStartDelay(1000);
        lettre_e.animate().translationY(-500).setDuration(2000).setStartDelay(1500);
        lettre_l.animate().translationY(-500).setDuration(2000).setStartDelay(2000);
        lettre_i.animate().translationY(-500).setDuration(2000).setStartDelay(2500);
       mt.animate().translationX(800).setDuration(2200).setStartDelay(3000);
        new Handler( ).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, MainActivity_inscrit_client.class);
                startActivity(intent);
                finish();
            }
        },duree)

;    }
}