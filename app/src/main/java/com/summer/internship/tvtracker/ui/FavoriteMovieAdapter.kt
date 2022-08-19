package com.summer.internship.tvtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.summer.internship.tvtracker.domain.FavoriteMovie
import com.summer.internship.tvtracker.databinding.ItemFavoriteMovieBinding

class FavoriteMovieAdapter(
    private val movies: ArrayList<FavoriteMovie>,
    private val imageLoader: ImageLoader,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemFavoriteMovieBinding,private val imageLoader: ImageLoader) :
        MoviesAdapter.ViewHolder(binding, imageLoader) {

        fun bind(
            movie: FavoriteMovie,
            clickListener: (String) -> Unit,
            clickDelete: (pos: Int) -> Unit
        ) {

            binding.textViewMovieTitle.text = movie.title

            imageLoader.loadImage(
                binding.root,
                "/6KyJeOW7vTW0czdR0S6wzXAcfmw.jpg",
                binding.imageViewMovie
            )

            binding.textViewDate.text = movie.data

            binding.buttonDelete.setOnClickListener {
                clickDelete(adapterPosition)
            }
            binding.root.setOnClickListener {
                clickListener(movie.title)
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