package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.LoginRequest;
import com.example.project_webapp.Service.HTTP.LoginResponse;
import com.example.project_webapp.Service.HTTP.RegisterRequest;
import com.example.project_webapp.Service.HTTP.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("login_api.php")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("register_api.php")
    Call<GlobalResponse> userRegister(@Body RegisterRequest registerRequest);

    @GET("user.php")
    Call<UserResponse> userDetail(@Query("id_user") String id_user);

}

