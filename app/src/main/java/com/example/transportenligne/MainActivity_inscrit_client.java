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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
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
                String url = "http://10.0.2.2:8000/api/add";
                //String url = "http://192.168.1.25:8000/api/list";

                // Create a JSON object with the data you want to send
                JSONObject requestData = new JSONObject();
                try {
                    requestData.put("username", mFullNameEditText.getText().toString().trim());
                    requestData.put("password", mPasswordEditText.getText().toString().trim());
                    requestData.put("email", mEmailEditText.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Declare the JsonObjectRequest variable

                JsonObjectRequest jsonRequest2;
                try {
                    // Create a new JsonObjectRequest with the request method, URL, and the JSON data
                    //JsonObjectRequest finalJsonRequest = jsonRequest;
                     jsonRequest2  = new JsonObjectRequest(Request.Method.POST, url, requestData,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Handle the response from the server
                                    // Process the response JSON object


                                    Log.d("MyApp","response est la suivante: "+response.toString());
                                    Toast.makeText(MainActivity_inscrit_client.this, "Inscription réussie!", Toast.LENGTH_SHORT).show();
                                    // String message = response.getString("message");
                                    // Handle the message as needed

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle errors that occurred during the request
                                    if (error instanceof TimeoutError) {
                                        // Retry the request
                                        Log.d("MyApp","retry ....");

                                    } else {
                                        error.printStackTrace();
                                    }
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

// Add the request to the RequestQueue
                jsonRequest2.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 60000; // Timeout in milliseconds
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 3; // Number of retries
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {
                        // You can log the retry attempt here
                        Volley.newRequestQueue(getApplicationContext()).add(jsonRequest2);
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(jsonRequest2);
                /////////////////////////
            };});}}