package com.summer.internship.tvtracker.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.Data.MoviesRemoteDataSource
import com.summer.internship.tvtracker.Data.MoviesRepositoryFactoryIMPL
import com.summer.internship.tvtracker.Domain.Movie
import com.summer.internship.tvtracker.Domain.MovieResponseListener
import com.summer.internship.tvtracker.UI.MoviesAdapter
import com.summer.internship.tvtracker.databinding.FragmentPopularBinding


class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesRepository = MoviesRepositoryFactoryIMPL.createMoviesRepository(
            MoviesRemoteDataSource
        )

        moviesRepository.getPopular(object : MovieResponseListener {
            override fun onMoviesReceived(list: List<Movie>) {
                val adapter = MoviesAdapter(list) {
                    showToast(it)
                }

                binding.apply {
                    rvMovies.run {
                        this.adapter = adapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }

            override fun onError() {
                showToast("Error loading movies")
            }

        })

    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}