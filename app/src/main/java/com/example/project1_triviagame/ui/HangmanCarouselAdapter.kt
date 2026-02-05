package com.example.project1_triviagame.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1_triviagame.R
import com.google.android.material.card.MaterialCardView

data class HangmanThemeOption(
    val id: Int,
    val label: String = "?",
    val textColorHex: String
    // later: val imageResId: Int? or val imageUrl: String?
)

class HangmanCarouselAdapter(
    private val items: List<HangmanThemeOption>
) : RecyclerView.Adapter<HangmanCarouselAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: MaterialCardView = itemView as MaterialCardView
        val mark: TextView = itemView.findViewById(R.id.tvMark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hangman_option, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.mark.text = item.label
        holder.mark.setTextColor(Color.parseColor(item.textColorHex))
    }

    override fun getItemCount(): Int = items.size
}