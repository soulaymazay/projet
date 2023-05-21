package com.example.transportenligne;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity_inscrit_infochauffeur extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1; // Constante pour identifier la demande de sélection d'image
    private EditText mPermisEdiText;
    private EditText mFullNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mVfpasswordEditText;
    private Button mSignupButton;
    private ImageView imageView;
    private Button importButton;
    private Uri selectedImageUri; // Uri de l'image sélectionnée

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.printf("test2");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrit_infochauffeur);

        mPermisEdiText = findViewById(R.id.marque);
        mFullNameEditText = findViewById(R.id.modele);
        mEmailEditText = findViewById(R.id.annee);
        mPasswordEditText = findViewById(R.id.mot1);
        mVfpasswordEditText = findViewById(R.id.mot2);
        mSignupButton = findViewById(R.id.button4);
        imageView = findViewById(R.id.image);
        importButton = findViewById(R.id.btn);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs des champs
                String fullName = mFullNameEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                String vfpassword = mVfpasswordEditText.getText().toString().trim();
                String permis = mPermisEdiText.getText().toString().trim();

                // Vérifier si les champs sont valides
                if (fullName.isEmpty()) {
                    mFullNameEditText.setError("Le nom complet est requis.");
                    mFullNameEditText.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    mEmailEditText.setError("L'adresse e-mail est requise.");
                    mEmailEditText.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    mPasswordEditText.setError("Le mot de passe est requis.");
                    mPasswordEditText.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    mPasswordEditText.setError("Le mot de passe doit comporter au moins 6 caractères.");
                    mPasswordEditText.requestFocus();
                    return;
                }

                if (!password.equals(vfpassword)) {
                    // Les mots de passe ne correspondent pas, afficher un message d'erreur
                    mVfpasswordEditText.setError("Les mots de passe ne correspondent pas");
                    mVfpasswordEditText.requestFocus();
                    return;
                }

                // Vérifier si une image a été sélectionnée
                if (selectedImageUri == null) {
                    // Aucune image sélectionnée, afficher un message d'erreur
                    // ou demander à l'utilisateur de sélectionner une image
                    return;
                }

                // Faire quelque chose avec les données de l'inscription et l'image sélectionnée
                // ...

                // Par exemple, vous pouvez enregistrer l'image dans la galerie du smartphone
                saveImageToGallery();
            }
        });

        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });
    }

    private void showImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Récupérer l'URI de l'image sélectionnée
            selectedImageUri = data.getData();

            try {
                // Charger l'image à partir de l'URI et l'afficher dans l'ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImageToGallery() {
        try {
            // Ouvrir un flux d'entrée pour lire l'image à partir de l'URI
            InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);

            // Créer un répertoire pour enregistrer l'image dans la galerie
            File galleryDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String imageName = "image.jpg";
            File imageFile = new File(galleryDir, imageName);

            // Ouvrir un flux de sortie pour écrire l'image dans le répertoire de la galerie
            OutputStream outputStream = new FileOutputStream(imageFile);

            // Copier les données de l'image de l'entrée vers la sortie
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Fermer les flux
            inputStream.close();
            outputStream.close();

            // Actualiser la galerie pour afficher la nouvelle image
            MediaStore.Images.Media.insertImage(getContentResolver(), imageFile.getAbsolutePath(), imageName, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
