package com.summer.internship.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.summer.internship.tvtracker.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    lateinit var movies: ArrayList<Movie>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Lookup the recyclerview in activity layout
        val rvMovies = binding.rvMovies as RecyclerView
        // Initialize contacts
        movies = Movie.createMovieList(20)
        // Create adapter passing in the sample user data
        val adapter = MoviesAdapter(movies){Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()}
        // Attach the adapter to the recyclerview to populate items
        rvMovies.adapter = adapter
        // Set layout manager to position the items
        rvMovies.layoutManager = LinearLayoutManager(requireContext())
    }
}