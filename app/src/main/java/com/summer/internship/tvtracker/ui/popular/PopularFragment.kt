package com.summer.internship.tvtracker.ui.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.ui.GlideImageLoader
import com.summer.internship.tvtracker.databinding.FragmentPopularBinding
import com.summer.internship.tvtracker.domain.Movie
import com.summer.internship.tvtracker.ui.MoviesAdapter
import com.summer.internship.tvtracker.ui.detailsScreen.DetailsScreenActivity


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

        val model: PopularViewModel by viewModels()

        model.getMovies().observe(viewLifecycleOwner, Observer<List<Movie>> { movies ->
            val adapter = MoviesAdapter(movies, GlideImageLoader()) {
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
        })


    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}