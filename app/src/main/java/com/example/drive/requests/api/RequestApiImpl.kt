package com.example.drive.requests.api

import android.content.Intent
import com.example.drive.response.AuthResponse
import com.example.drive.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthApiImpl : Callback<AuthResponse> {
    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
        println("Ошибка авторизации: ${t.message}")
    }

    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
        if (response.isSuccessful) {
            LoginActivity.authData = response.body()

            println("Ответ получен: ${response.body()}")
        }
        if (response.code() == 403) {
            println("Не верно ввели пароль или логин")
        }
    }
}