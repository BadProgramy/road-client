package com.example.drive.requests.api

import com.example.drive.requests.AuthRequest
import com.example.drive.response.AuthResponse
import retrofit2.Call
import retrofit2.http.*


interface AuthApi {

    companion object {
        lateinit var authUser: String
    }

    @GET("/api/internal/login/result")
    fun login(@Header("Authorization") auth: String? = authUser): Call<AuthResponse>

    /*@FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/login")
    fun postLogIn(
        @Field("password") password: String,
        @Field("username") username: String
    ): Call<AuthResponse>*/
}