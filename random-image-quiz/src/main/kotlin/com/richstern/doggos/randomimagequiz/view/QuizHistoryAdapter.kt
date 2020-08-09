package com.richstern.doggos.randomimagequiz.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.richstern.doggos.model.Breed
import com.richstern.doggos.randomimagequiz.R

class QuizHistoryAdapter : RecyclerView.Adapter<QuizHistoryAdapter.HistoryItemViewHolder>() {

    private val historyItems = mutableListOf<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.view_history_item, parent, false)
        return HistoryItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val item = historyItems[position]
        holder.bind(item)
    }

    fun setItems(items: List<Breed>) {
        historyItems.clear()
        historyItems.addAll(items)
        notifyDataSetChanged()
    }

    class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView by lazy {
            itemView.findViewById<TextView>(R.id.history_item_breed_name)
        }
        private val imageView by lazy { itemView.findViewById<ImageView>(R.id.history_item_image) }

        fun bind(item: Breed) {
            nameTextView.text = item.name
            Glide.with(itemView)
                .load(item.imageUrl)
                .centerCrop()
                .into(imageView)
        }

    }
}