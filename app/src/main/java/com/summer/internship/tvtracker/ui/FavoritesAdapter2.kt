package com.summer.internship.tvtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.summer.internship.tvtracker.databinding.ItemFavoriteMovieBinding
import com.summer.internship.tvtracker.domain.MovieUI

class FavoritesAdapter2(
    private val movies: ArrayList<MovieUI>,
    private val imageLoader: ImageLoader,
    private val clickListener: (Long) -> Unit,
    private val deleteListener: (Long) -> Unit,
) : BaseMovieAdapter<MovieUI>(movies, clickListener) {

    class MoviesViewHolder(
        private val binding: ItemFavoriteMovieBinding,
        private val imageLoader: ImageLoader,
        private val deleteListener: (Long) -> Unit,
        private val notifyAdapter: (Int) -> Unit,
    ) : BaseViewHolder<MovieUI>(binding, imageLoader) {

        override fun bind(movie: MovieUI, clickListener: (Long) -> Unit) {
            super.bind(movie, clickListener)
            binding.textViewMovieTitle.text = movie.title


            binding.buttonDelete.setOnClickListener {
                deleteListener(movie.id)
                notifyAdapter(adapterPosition)
            }
        }

        override val imageView: ImageView
            get() = binding.imageViewMovie

    }

    private fun deletePosition(pos: Int) {
        movies.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieUI> {
        return MoviesViewHolder(
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader, deleteListener, this::deletePosition
        )
    }
}