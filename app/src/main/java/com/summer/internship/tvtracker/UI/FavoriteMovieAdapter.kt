package com.summer.internship.tvtracker.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.summer.internship.tvtracker.Domain.FavoriteMovie
import com.summer.internship.tvtracker.databinding.ItemFavoriteMovieBinding

class FavoriteMovieAdapter(
    private val movies: ArrayList<FavoriteMovie>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemFavoriteMovieBinding) :
        MoviesAdapter.ViewHolder(binding) {

        fun bind(
            movie: FavoriteMovie,
            clickListener: (String) -> Unit,
            clickDelete: (pos: Int) -> Unit
        ) {

            binding.textViewMovieTitle.text = movie.title

            loadImage(
                binding.root,
                "https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300",
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
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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