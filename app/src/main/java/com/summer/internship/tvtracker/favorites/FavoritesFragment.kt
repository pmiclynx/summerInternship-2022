package com.summer.internship.tvtracker.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.FavoriteMovie
import com.summer.internship.tvtracker.FavoriteMovieAdapter
import com.summer.internship.tvtracker.Movie
import com.summer.internship.tvtracker.MoviesAdapter
import com.summer.internship.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.internship.tvtracker.popular.showToast

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    lateinit var movies: ArrayList<FavoriteMovie>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies = FavoriteMovie.createMovieList(20)

        val adapter = FavoriteMovieAdapter(movies) {
            showToast(it)
        }

        binding.apply {
            rvMovies.run {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}