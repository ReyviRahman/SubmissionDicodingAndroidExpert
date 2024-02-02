package com.example.submissionexpert.core.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionexpert.core.domain.model.Game
import com.example.submissionexpert.databinding.ItemListGameBinding
import com.example.submissionexpert.detail.DetailGameActivity

class GameAdapter: ListAdapter<Game, GameAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemListGameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Game) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivGame)
                tvItemTitle.text = data.name
                tvReleaseDate.text = data.releaseDate
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailGameActivity::class.java)
//                intent.putExtra(DetailTourismActivity.EXTRA_DATA, data)
                itemView.context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.MyViewHolder {
        val binding = ItemListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameAdapter.MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Game> =
            object : DiffUtil.ItemCallback<Game>() {
                override fun areItemsTheSame(
                    oldItem: Game,
                    newItem: Game
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: Game,
                    newItem: Game
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}