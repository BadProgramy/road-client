package com.example.drive.requests.api

import com.example.drive.CityActivity
import com.example.drive.mocks.AuthMock
import com.example.drive.mocks.CityMock
import com.example.drive.model.City
import com.example.drive.response.AuthResponse
import com.example.drive.response.CityResponse
import com.example.drive.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CityApi {
    @GET("/api/internal/city")
    fun getCity(@Header("Authorization") auth: String? = AuthApi.authUser): Call<List<CityResponse>>
}

class CityApiImpl : Callback<List<CityResponse>> {
    override fun onFailure(call: Call<List<CityResponse>>, t: Throwable) {
        println("Ошибка авторизации: ${t.message}")
        CityActivity.city = CityMock.city()
        LoginActivity.authData = AuthMock.auth()
    }

    override fun onResponse(call: Call<List<CityResponse>>, response: Response<List<CityResponse>>) {
        if (response.isSuccessful) {
            CityActivity.city = response.body()!!.getCity()
            LoginActivity.authData = AuthResponse(status = response.code())
            println("Ответ получен: ${response.body()}")
            return
        }
        if (response.code() == 403) {
            println("Не верно ввели пароль или логин")
            return
        }
        println("Статус код ответа ${response.code()}")
        LoginActivity.authData = AuthMock.auth()
        CityActivity.city = CityMock.city()
    }

    private fun List<CityResponse>.getCity(): List<City> =
        this.map { City(name = it.name) }
}