package com.summer.internship.tvtracker.ui.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.summer.internship.tvtracker.databinding.FragmentPopularBinding
import com.summer.internship.tvtracker.ui.baseMoviesFragment.BaseFragment


class PopularFragment : BaseFragment<FragmentPopularBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model: PopularViewModel by viewModels()
        model.getMovies().observe(viewLifecycleOwner) { moviesList ->
            loadMovies(moviesList)
        }
    }

    override fun getViewBinding(inflater: LayoutInflater,cont: ViewGroup?): FragmentPopularBinding {
        return FragmentPopularBinding.inflate(inflater, cont, false)
    }

    override val recyclerView: RecyclerView
        get() = binding.rvMovies

//    override fun getRecycle(): RecyclerView {
//        return binding.rvMovies
//    }

//    override val recyclerView: RecyclerView by lazy {
//        Log.d("aaaaaaa","da")
//        binding.rvMovies }
}