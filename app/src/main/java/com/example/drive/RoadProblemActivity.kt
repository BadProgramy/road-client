package com.example.drive

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drive.adapter.RVAdapter
import com.example.drive.model.RoadProblem
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import com.example.drive.mocks.CityMock
import com.example.drive.model.City

class RoadProblemActivity : AppCompatActivity() {

    companion object {
        lateinit var rv: RecyclerView
        var cards: MutableList<RoadProblem>? = null
        var city: City = CityMock.city()[0]
        lateinit var roadProblemOnClickListener: RoadProblemOnClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_road_problem)
        rv = findViewById(R.id.rv)
        roadProblemOnClickListener = RoadProblemOnClickListener(this)
        val llm = LinearLayoutManager(this)
        rv.setLayoutManager(llm)
        val buttonActivityMain = findViewById<Button>(R.id.addRoadProblem)
        buttonActivityMain.setOnClickListener(roadProblemOnClickListener)
        initializeAdapter()
    }

    private fun initializeAdapter() {
        val adapter = RVAdapter(cards!!.filter { it.city.name == city.name })
        rv.adapter = adapter
    }
}

class RoadProblemOnClickListener(private val context: Context) : View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cv -> {
                val selectedItemPosition = RoadProblemActivity.rv.getChildPosition(v)
                val viewHolder = RoadProblemActivity.rv.findViewHolderForPosition(selectedItemPosition)
                RoadProblemMapActivity.roadProblem = RoadProblemActivity.cards!![viewHolder!!.layoutPosition]
                val intent = Intent(context, RoadProblemMapActivity::class.java)
                startActivity(context, intent, null)
            }

            R.id.addRoadProblem -> {

                val intent = Intent(context, AddRoadProblemActivity::class.java)
                startActivity(context, intent, null)
            }
        }
    }
}
