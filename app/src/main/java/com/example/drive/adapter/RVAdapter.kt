package com.example.drive.adapter

import com.example.drive.R
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drive.RoadProblemActivity
import com.example.drive.model.RoadProblem

class RVAdapter internal constructor(internal var cards: List<RoadProblem>) :
    RecyclerView.Adapter<RVAdapter.CardViewHolder>() {

    class CardViewHolder internal constructor(internal var cardView: CardView) :
        RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_road_problem, parent, false) as CardView
        cv.setOnClickListener(RoadProblemActivity.roadProblemOnClickListener)
        return CardViewHolder(cv)
    }

    override fun onBindViewHolder(cardViewHolder: CardViewHolder, position: Int) {
        val cardView = cardViewHolder.cardView
        val title = cardView.findViewById<View>(R.id.title) as TextView
        title.setText(cards[position].title)
        val content = cardView.findViewById<View>(R.id.content) as TextView
        content.setText(cards[position].description)

    }

    override fun getItemCount(): Int {
        return cards.size
    }
}