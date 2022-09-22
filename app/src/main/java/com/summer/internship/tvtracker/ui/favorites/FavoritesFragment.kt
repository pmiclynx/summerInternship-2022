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
import com.summer.internship.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.internship.tvtracker.domain.FavoriteMovieUI
import com.summer.internship.tvtracker.ui.FavoriteMovieAdapter
import com.summer.internship.tvtracker.ui.GlideImageLoader
import com.summer.internship.tvtracker.ui.detailsScreen.DetailsScreenActivity

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    lateinit var movies: ArrayList<FavoriteMovieUI>
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

        model.loadMovies()

        model.getMovies()
            .observe(viewLifecycleOwner, Observer<List<FavoriteMovieUI>> { movies ->
                val adapter = FavoriteMovieAdapter(ArrayList(movies), GlideImageLoader(), {
                    val intent = Intent(requireContext(), DetailsScreenActivity::class.java)
                    intent.putExtra("id", it)
                    startActivity(intent)
                }) {
                    model.deleteFavorite(it)
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