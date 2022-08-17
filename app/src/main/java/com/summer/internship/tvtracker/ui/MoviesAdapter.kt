package com.summer.internship.tvtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.summer.internship.tvtracker.domain.Movie
import com.summer.internship.tvtracker.databinding.ItemMovieBinding

class MoviesAdapter(
    private val movies: List<Movie>,
    private val imageLoader: ImageLoader,
    private val clickListener: (Long) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    open class ViewHolder(private val binding: ViewBinding, private val imageLoader: ImageLoader) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie, clickListener: (Long) -> Unit) {
            if (binding !is ItemMovieBinding) return

            binding.textViewMovieTitle.text = movie.title
            if (movie.url !== null)
                imageLoader.loadImage(
                    binding.root,
                    movie.url,
                    binding.imageViewMovie
                )

            binding.root.setOnClickListener {
                clickListener(movie.id)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie: Movie = movies[position]
        viewHolder.bind(movie, clickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}