package com.summer.internship.tvtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.summer.internship.tvtracker.databinding.ItemMovieBinding

class MoviesAdapter (private val movies: List<Movie>,
                     private val clickListener: (String) ->Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleMovie = itemView.findViewById<TextView>(R.id.textViewMovieTitle)
        val imageMovie = itemView.findViewById<ImageView>(R.id.imageViewMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_movie, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: MoviesAdapter.ViewHolder, position: Int) {
        val movie: Movie = movies.get(position)
        viewHolder.titleMovie.setText(movie.title)
        viewHolder.itemView.setOnClickListener{clickListener(movies[position].title)}
        Glide.with(viewHolder.itemView).load("https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300").into(viewHolder.imageMovie);
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}