package com.vt.teravin_test.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vt.teravin_test.core.source.local.entity.MovieEntity
import com.vt.teravin_test.databinding.ItemMovieBinding


class HomeAdapter : ListAdapter<MovieEntity, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
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
        fun bindTo(movieEntity: MovieEntity) {
            with(binding) {
                tvTitle.text = movieEntity.title ?: ""
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieEntity> =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }
}