package com.summer.internship.tvtracker.ui.baseMoviesFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.summer.internship.tvtracker.domain.MovieUI
import com.summer.internship.tvtracker.ui.GlideImageLoader
import com.summer.internship.tvtracker.ui.MoviesAdapter
import com.summer.internship.tvtracker.ui.detailsScreen.DetailsScreenActivity

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(inflater: LayoutInflater,cont: ViewGroup?): VBinding

    protected abstract val recyclerView: RecyclerView

//    protected abstract fun getRecycle():RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater,container)
        return binding.root
    }

    fun observeMoviesLiveData(movies: LiveData<List<MovieUI>>) {
        movies.observe(viewLifecycleOwner) { moviesList ->
            loadMovies(moviesList)
        }
    }

    fun loadMovies(list: List<MovieUI>) {
        val adapter =
            MoviesAdapter(list, GlideImageLoader()) {
                val intent = Intent(requireContext(), DetailsScreenActivity::class.java)
                intent.putExtra("id", it)
                startActivity(intent)

            }
        recyclerView.run {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

}