package com.example.transportenligne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class test2 extends AppCompatActivity {
    ListView lstch;
    String nom, moyendetran;
    HashMap<String, String> map;
    Params p = new Params();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlistedes_chauffeurs);

        lstch = findViewById(R.id.lstch);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nom = extras.getString("nom");
            moyendetran = extras.getString("moyendetran");
        }

        map = new HashMap<String, String>();
        map.put("nom", nom);
        map.put("moyendetran", moyendetran);
        p.values.add(map);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                p.values,
                R.layout.item_chauff,
                new String[]{"nom", "moyendetran"},
                new int[]{R.id.nomchauff, R.id.moyen}
        );
        lstch.setAdapter(adapter);
    }
}
