package com.summer.internship.tvtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.summer.internship.tvtracker.databinding.ItemMovieBinding
import com.summer.internship.tvtracker.domain.MovieUI

class MoviesAdapter2(
    private val movies: List<MovieUI>,
    private val imageLoader: ImageLoader,
    private val clickListener: (Long) -> Unit
) : BaseMovieAdapter<MovieUI>(movies, clickListener) {

    class MoviesViewHolder(
        private val binding: ItemMovieBinding,
        private val imageLoader: ImageLoader
    ) : BaseViewHolder<MovieUI>(binding, imageLoader) {

        override fun bind(movie: MovieUI, clickListener: (Long) -> Unit) {
            super.bind(movie, clickListener)
            binding.textViewMovieTitle.text = movie.title
        }

        override val imageView: ImageView
            get() = binding.imageViewMovie

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieUI> {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader
        )
    }
}