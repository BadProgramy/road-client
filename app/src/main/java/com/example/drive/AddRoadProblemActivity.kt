package com.example.drive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.drive.model.RoadProblem

class AddRoadProblemActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_road_problem)
        val buttonAddRoadProblem = findViewById<Button>(R.id.addRoadProblemSubmit)
        val buttonChoose = findViewById<Button>(R.id.chooseAddGeoLocation)
        buttonAddRoadProblem.setOnClickListener(this)
        buttonChoose.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.chooseAddGeoLocation -> {
                val intent = Intent(this, RoadProblemMapActivity::class.java)
                startActivity(intent)
            }

            R.id.addRoadProblemSubmit -> {
                RoadProblemActivity.cards!!.add(
                    RoadProblem(
                        title = findViewById<EditText>(R.id.titleRoadProblem).text.toString(),
                        description = findViewById<EditText>(R.id.descriptionRoadProblem).text.toString(),
                        geoLocation = RoadProblemMapActivity.geoLocationClick!!,
                        city = RoadProblemActivity.city
                    )
                )
                val intent = Intent(this, RoadProblemActivity::class.java)
                startActivity(intent)

                // Отправить запрос на создание проблемной зоны на сервер
            }
        }
    }
}
