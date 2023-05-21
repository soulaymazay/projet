package com.example.transportenligne;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.transportenligne.Error;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity_inscrit_client extends AppCompatActivity {

    private EditText mFullNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mVfpasswordEditText;
    private Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.printf("test1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrit_client);

        connectButton= (Button)findViewById(R.id.button4);
        mFullNameEditText = (EditText)findViewById(R.id.T1);
        mEmailEditText =(EditText) findViewById(R.id.T2);
        mPasswordEditText = (EditText)findViewById(R.id.mp1);
        mVfpasswordEditText = (EditText)findViewById(R.id.mp2);



        connectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){


                // Récupérer les valeurs des champs
                System.out.printf("succsees");
                String mFullName = mFullNameEditText.getText().toString().trim();
                String mEmail = mEmailEditText.getText().toString().trim();
                String mPassword = mPasswordEditText.getText().toString().trim();
                String vfpassword = mVfpasswordEditText.getText().toString().trim();

                // Vérifier si les champs sont valides
                if (mFullName.isEmpty()) {
                    mFullNameEditText.setError("Le nom complet est requis.");
                    mFullNameEditText.requestFocus();
                    return;
                }

                if (mEmail.isEmpty()) {
                    mEmailEditText.setError("L'adresse e-mail est requise.");
                    mEmailEditText.requestFocus();
                    return;
                }

                if (!mEmail.contains(".") || !mEmail.contains("@")) {
                    mEmailEditText.setError("L'adresse e-mail n'est pas valide.");
                    mEmailEditText.requestFocus();
                    return;
                }

                if (mPassword.isEmpty()) {
                    mPasswordEditText.setError("Le mot de passe est requis.");
                    mPasswordEditText.requestFocus();
                    return;
                }

                if (mPassword.length() < 6) {
                    mPasswordEditText.setError("Le mot de passe doit comporter au moins 6 caractères.");
                    mPasswordEditText.requestFocus();
                    return;
                }

                if (!mPassword.equals(vfpassword)) {
                    // Les mots de passe ne correspondent pas, afficher un message d'erreur
                    mVfpasswordEditText.setError("Les mots de passe ne correspondent pas");
                    mVfpasswordEditText.requestFocus();
                    return;
                }

                // Envoyer la requête POST avec Volley
                //String url = "https://10.0.2.2:8080/api/clients";
                String url = "http://192.168.1.25:8000/api/list";

                System.out.printf(url);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Gérer la réponse du serveur
                                Toast.makeText(MainActivity_inscrit_client.this, "Inscription réussie!", Toast.LENGTH_SHORT).show();

                                // Naviguer vers une autre activité
                                Intent intent = new Intent(MainActivity_inscrit_client.this, MainActivity_authentification.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // La requête a échoué
                        System.out.printf("error");

                        Log.d("MyApp",error.toString());

                     /*   if (error.networkResponse != null && error.networkResponse.data != null) {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Error errorResponse = gsonBuilder.create().fromJson(new String(error.networkResponse.data), Error.class);
                            Toast.makeText(MainActivity_inscrit_client.this, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity_inscrit_client.this, "Unable to connectxx", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", mFullNameEditText.getText().toString().trim());
                        params.put("password", mPasswordEditText.getText().toString().trim());
                        params.put("email", mEmailEditText.getText().toString().trim());
                        Log.d("MyApp",params.toString());
                        return params;
                    }
                };
                queue.add(stringRequest);};});}}