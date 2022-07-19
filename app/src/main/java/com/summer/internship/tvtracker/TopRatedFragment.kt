package com.summer.internship.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = Movie.createMovieList(20)

        binding.rvMovies.apply {
            adapter = MoviesAdapter(movies) {
                showToast(it)
            }

            layoutManager = LinearLayoutManager(context)
        }
    }
}