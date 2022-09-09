package com.summer.internship.tvtracker.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.ui.GlideImageLoader
import com.summer.internship.tvtracker.domain.FavoriteMovie
import com.summer.internship.tvtracker.ui.FavoriteMovieAdapter
import com.summer.internship.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.internship.tvtracker.ui.detailsScreen.DetailsScreenActivity
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


        val model: FavoritesViewModel by viewModels()

        model.loadFavoriteMovies()

        model.getFavoriteMovies()
            .observe(viewLifecycleOwner, Observer<List<FavoriteMovie>> { movies ->
                val adapter = FavoriteMovieAdapter(ArrayList(movies), GlideImageLoader()) {
                    model.deleteFavorite(it.id)
                }

                binding.apply {
                    rvMovies.run {
                        this.adapter = adapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            })
    }
}