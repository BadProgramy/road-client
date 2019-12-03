package com.example.drive.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.drive.CityActivity
import com.example.drive.R
import com.example.drive.requests.api.AuthApi
import com.example.drive.response.AuthResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import android.widget.ProgressBar
import com.example.drive.requests.api.CityApi
import com.example.drive.requests.api.CityApiImpl
import com.example.drive.response.CityResponse

open class LoginActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://85.236.184.43:8081")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()

        var authData: AuthResponse? = null
        var progressBar: ProgressBar? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)
        val buttonActivityMain = findViewById<Button>(R.id.login)
        buttonActivityMain.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        authData = null
        when (v?.id) {
            R.id.login -> {
                val base = username.text.toString().plus(":").plus(password.text.toString())
                AuthApi.authUser = "Basic " + Base64.getEncoder().encodeToString(base.toByteArray())

                val cityApi = retrofit.create(CityApi::class.java)
                val callCity: Call<List<CityResponse>> = cityApi.getCity()
                callCity.enqueue(CityApiImpl())

                val intent = Intent(this, CityActivity::class.java)
                progressBar = findViewById(R.id.progressBar)
                progressBar!!.secondaryProgress = 100
                progressBar!!.progress = 10
                progressBar!!.max = 100
                progressBar!!.visibility = View.VISIBLE
                Thread {
                    while (authData == null && CityActivity.city.isNullOrEmpty()) {
                        println("authData = $authData")
                    }
                    startActivity(intent) // после успеха перебрасываем его в другое окно
                }.start()
            }
        }
    }
}
