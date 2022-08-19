package com.summer.internship.tvtracker.ui.topRated

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.ui.GlideImageLoader
import com.summer.internship.tvtracker.domain.Movie
import com.summer.internship.tvtracker.ui.MoviesAdapter
import com.summer.internship.tvtracker.databinding.FragmentTopRatedBinding
import com.summer.internship.tvtracker.ui.detailsScreen.DetailsScreenActivity

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

        val model: TopRatedViewModel by viewModels()

        model.getMovies().observe(viewLifecycleOwner, Observer<List<Movie>> { movies ->
                loadMovies(movies)
            })
    }

    private fun loadMovies(list:List<Movie>){
        val adapter = MoviesAdapter(list, GlideImageLoader()) {
            val intent = Intent(requireContext(), DetailsScreenActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
        }

        binding.apply {
            rvMovies.run {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        Log.i("aaaaa", "ondestroyView")
        super.onDestroyView()
    }
}