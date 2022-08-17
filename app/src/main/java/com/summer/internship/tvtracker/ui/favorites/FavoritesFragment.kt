package com.summer.internship.tvtracker.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.ui.TheMovieDbGlideImageLoader
import com.summer.internship.tvtracker.domain.FavoriteMovie
import com.summer.internship.tvtracker.ui.FavoriteMovieAdapter
import com.summer.internship.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.internship.tvtracker.ui.popular.showToast

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    lateinit var movies: ArrayList<FavoriteMovie>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies = FavoriteMovie.createMovieList(20)

        val adapter = FavoriteMovieAdapter(movies, TheMovieDbGlideImageLoader()) {
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