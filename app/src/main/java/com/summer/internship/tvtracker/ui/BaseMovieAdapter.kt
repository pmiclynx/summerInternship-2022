package com.summer.internship.tvtracker.ui

import android.widget.ImageView
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.summer.internship.tvtracker.domain.MovieUI

abstract class BaseMovieAdapter<T : MovieUI>(
    private val movies: List<T>,
    private val clickListener: (Long) -> Unit
) :
    RecyclerView.Adapter<BaseMovieAdapter.BaseViewHolder<T>>() {

    abstract class BaseViewHolder<T : MovieUI>(
        private val binding: ViewBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {

        protected abstract val imageView: ImageView

        @CallSuper
        open fun bind(movie: T, clickListener: (Long) -> Unit) {
            if (movie.url !== null)
                imageLoader.loadImage(
                    binding.root,
                    movie.url,
                    imageView
                )
            binding.root.setOnClickListener {
                clickListener(movie.id)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<T>, position: Int) {
        val movie: T = movies[position]
        viewHolder.bind(movie, clickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}