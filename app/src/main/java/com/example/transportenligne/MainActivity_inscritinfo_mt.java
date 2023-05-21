package com.example.transportenligne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity_inscritinfo_mt extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button buttonChoose;
    private ImageView imageView;

    private EditText mMarqueEdiText;
    private EditText mModeleEditText;
    private EditText mAnneeEditText;
    private Button mSignupButton;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.printf("test3");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscritinfo_mt);

        mMarqueEdiText = findViewById(R.id.marque);
        mModeleEditText = findViewById(R.id.modele);
        mAnneeEditText = findViewById(R.id.annee);
        mSignupButton = findViewById(R.id.button4);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marque = mMarqueEdiText.getText().toString().trim();
                String modele = mModeleEditText.getText().toString().trim();
                String annee = mAnneeEditText.getText().toString().trim();

                if (marque.isEmpty()) {
                    mMarqueEdiText.setError("La marque complète est requise.");
                    mMarqueEdiText.requestFocus();
                    return;
                }
                if (modele.isEmpty()) {
                    mModeleEditText.setError("Le modèle est requis.");
                    mModeleEditText.requestFocus();
                    return;
                }

                if (annee.isEmpty()) {
                    mAnneeEditText.setError("L'année est requise.");
                    mAnneeEditText.requestFocus();
                    return;
                }
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int selectedYear;
                try {
                    selectedYear = Integer.parseInt(annee);
                } catch (NumberFormatException e) {
                    mAnneeEditText.setError("L'année doit être composée uniquement de chiffres.");
                    mAnneeEditText.requestFocus();
                    return;
                }
                if (selectedYear > currentYear) {
                    mAnneeEditText.setError("L'année ne doit pas être dans le futur.");
                    mAnneeEditText.requestFocus();
                    return;
                }
                buttonChoose = findViewById(R.id.buttonChoose);
                imageView = findViewById(R.id.imageView);

                buttonChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showImageChooser();
                    }
                });
            }

            private void showImageChooser() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choisir une image"), PICK_IMAGE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null ) {
            Uri filePath = data.getData();
            try {
                // Récupère l'image à partir de l'URI
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Affiche l'image sur l'ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
