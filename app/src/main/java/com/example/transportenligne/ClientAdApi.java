package com.example.transportenligne;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientAdApi {

    @POST("add")
    Call<ClientAdd> createClientAdd(@Body ClientAdd clientA);


}
