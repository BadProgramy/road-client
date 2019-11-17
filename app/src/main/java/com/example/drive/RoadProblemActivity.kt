package com.example.drive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drive.adapter.RVAdapter
import com.example.drive.model.RoadProblem

class RoadProblemActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var cards: List<RoadProblem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_road_problem)

        rv = findViewById(R.id.rv)

        val llm = LinearLayoutManager(this)
        rv.setLayoutManager(llm)

        initializeData()
        initializeAdapter()
    }

    private fun initializeData() {
        cards = listOf(
            RoadProblem("Card 1", "Content 1"),
            RoadProblem("Card 2", "Content 2"),
            RoadProblem("Card 3", "Content 3"),
            RoadProblem("Card 4", "Content 4")
        )
    }

    private fun initializeAdapter() {
        val adapter = RVAdapter(cards)
        rv.adapter = adapter
    }
}
