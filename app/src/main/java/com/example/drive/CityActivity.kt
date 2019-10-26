package com.example.drive

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.drive.ui.login.LoginActivity

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val statusAuth = findViewById<TextView>(R.id.textView2)
        statusAuth.text = LoginActivity.authData?.status.toString()
    }
}