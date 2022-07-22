package com.summer.internship.tvtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.summer.internship.tvtracker.databinding.ItemMovieBinding

class MoviesAdapter(
    private val movies: List<Movie>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    open class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        protected fun loadImage(view: View, url: String, img: ImageView) {
            Glide.with(view)
                .load(url)
                .into(img)
        }

        fun bind(movie: Movie, clickListener: (String) -> Unit) {
            if (binding !is ItemMovieBinding) return

            binding.textViewMovieTitle.text = movie.title
            loadImage(
                binding.root,
                "https://image.tmdb.org/t/p/w500"+movie.url,
                binding.imageViewMovie
            )

            binding.root.setOnClickListener {
                clickListener(movie.title)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: MoviesAdapter.ViewHolder, position: Int) {
        val movie: Movie = movies[position]
        viewHolder.bind(movie, clickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}