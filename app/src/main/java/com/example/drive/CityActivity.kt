package com.example.drive

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.drive.mocks.CityMock
import com.example.drive.model.City
import com.example.drive.requests.api.RoadProblemApi
import com.example.drive.requests.api.RoadProblemApiImpl
import com.example.drive.response.RoadProblemResponse
import com.example.drive.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_city.view.*
import retrofit2.Call

class CityActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var city: List<City> = emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LoginActivity.progressBar!!.visibility = View.INVISIBLE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        val cityList = city.map { it.name }
        val spinner = findViewById<Spinner>(R.id.cities)
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
                val roadProblemApi = LoginActivity.retrofit.create(RoadProblemApi::class.java)
                val chooseItem = findViewById<Spinner>(R.id.cities)
                val callRoadProblem: Call<List<RoadProblemResponse>> = roadProblemApi.getRoadProblemByCity(chooseItem.selectedItem.toString())
                callRoadProblem.enqueue(RoadProblemApiImpl())
                val intent = Intent(this, RoadProblemActivity::class.java)
                RoadProblemActivity.city = CityMock.city().first { it.name == chooseItem.selectedItem.toString() }
                Thread {
                    //RoadProblemActivity.cards = null
                    while (RoadProblemActivity.cards.isNullOrEmpty()) {
                        println("RoadProblemActivity.cards = ${RoadProblemActivity.cards}")
                    }
                    startActivity(intent) // после успеха перебрасываем его в другое окно
                }.start()
            }
        }
    }
}