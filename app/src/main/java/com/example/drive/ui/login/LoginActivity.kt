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
import com.example.drive.requests.api.AuthApiImpl
import com.example.drive.response.AuthResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import android.widget.ProgressBar
import android.widget.TextView
import android.os.Handler


open class LoginActivity : AppCompatActivity(), View.OnClickListener {


    val retrofit = Retrofit.Builder()
        .baseUrl("http://85.236.182.178:8081")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build()

    companion object {
        var authData: AuthResponse? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //MapKitFactory.setApiKey("3c32f6da-350f-4245-bec1-f85c14739be3")
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)
        val buttonActivityMain = findViewById<Button>(R.id.login)
        buttonActivityMain.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        when (v?.id) {
            R.id.login -> {
                val base = username.text.toString().plus(":").plus(password.text.toString())
                AuthApi.authUser = "Basic " + Base64.getEncoder().encodeToString(base.toByteArray())

                val api = retrofit.create(AuthApi::class.java)

                val call: Call<AuthResponse> = api.login()
                call.enqueue(AuthApiImpl())

                val intent = Intent(this, CityActivity::class.java)
                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                progressBar.secondaryProgress = 100
                progressBar.progress = 10
                progressBar.max = 100
                progressBar.visibility = View.VISIBLE
                Thread {
                    while (authData == null) {
                        println("Здесь у пользователя будет анимация ожидании, мы не пустим его пока не получим ответ от сервера")
                    }
                    startActivity(intent) // после успеха перебрасываем его в другое окно
                }.start()
            }
        }
    }
}
