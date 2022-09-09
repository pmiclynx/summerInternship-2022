package com.summer.internship.tvtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.summer.internship.tvtracker.domain.FavoriteMovie
import com.summer.internship.tvtracker.databinding.ItemFavoriteMovieBinding

class FavoriteMovieAdapter(
    private val movies: ArrayList<FavoriteMovie>,
    private val imageLoader: ImageLoader,
    private val clickListener: (FavoriteMovie) -> Unit
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemFavoriteMovieBinding,
        private val imageLoader: ImageLoader
    ) :
        MoviesAdapter.ViewHolder(binding, imageLoader) {

        fun bind(
            movie: FavoriteMovie,
            clickListener: (FavoriteMovie) -> Unit,
            clickDelete: (pos: Int) -> Unit
        ) {

            binding.textViewMovieTitle.text = movie.title

            imageLoader.loadImage(
                binding.root,
                movie.url,
                binding.imageViewMovie
            )

            binding.textViewDate.text = movie.data

            binding.buttonDelete.setOnClickListener {
                clickDelete(adapterPosition)
                clickListener(movie)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie: FavoriteMovie = movies[position]
        viewHolder.bind(movie, clickListener) {
            movies.removeAt(it)
            notifyItemRemoved(it)
            notifyItemRangeChanged(it, 1)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}