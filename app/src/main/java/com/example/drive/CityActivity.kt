package com.example.drive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.drive.ui.login.LoginActivity

class CityActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val cityList = listOf("Самара", "Москва")
        val statusAuth = findViewById<TextView>(R.id.textView2)
        statusAuth.text = LoginActivity.authData?.status.toString()

        val spinner = findViewById<Spinner>(R.id.cities);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinner.adapter = adapter

        val buttonActivityMain = findViewById<Button>(R.id.choiseCity)
        buttonActivityMain.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.choiseCity -> {
                val intent = Intent(this, RoadProblemActivity::class.java)
                startActivity(intent)
            }
        }
    }
}