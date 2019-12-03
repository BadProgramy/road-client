package com.example.drive.requests.api

import com.example.drive.mocks.AuthMock
import com.example.drive.response.AuthResponse
import com.example.drive.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    companion object {
        lateinit var authUser: String
    }

    @GET("/api/internal/login/result")
    fun login(@Header("Authorization") auth: String? = authUser): Call<AuthResponse>
}

class AuthApiImpl : Callback<AuthResponse> {
    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
        println("Ошибка авторизации: ${t.message}")
        LoginActivity.authData = AuthMock.auth()
    }

    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
        if (response.isSuccessful) {
            LoginActivity.authData = AuthResponse(status = response.code())
            println("Ответ получен: ${response.body()}")
            return
        }
        if (response.code() == 403) {
            println("Не верно ввели пароль или логин")
            return
        }

        LoginActivity.authData = AuthMock.auth()
    }
}