package com.vt.teravin_test.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.databinding.ItemMovieBinding

class HomeAdapter : ListAdapter<Movie, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindTo(data)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(movieEntity: Movie) {
            val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
            with(binding) {
                Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movieEntity.posterPath)
                    .into(binding.ivItemImage)
                tvTitle.text = movieEntity.popularity.toString()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Movie,
                    newItem: Movie
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }
}