package com.summer.internship.tvtracker.topRated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.internship.tvtracker.Movie
import com.summer.internship.tvtracker.MoviesAdapter
import com.summer.internship.tvtracker.QuoteList
import com.summer.internship.tvtracker.Result
import com.summer.internship.tvtracker.Themoviedb
import com.summer.internship.tvtracker.databinding.FragmentTopRatedBinding
import com.summer.internship.tvtracker.popular.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val themoviedb: Themoviedb = retrofit.create(Themoviedb::class.java)

        val apiCall: Call<QuoteList> = themoviedb.getTopRated("563e3b53b3c3a7da6ceae87959d74162")

        apiCall.enqueue(object : Callback<QuoteList> {
            override fun onResponse(call: Call<QuoteList>, response: Response<QuoteList>) {

                val results: List<Result>? = response.body()?.results

                val list=
                    results?.let {
                        it.map { result ->
                            Movie(result.name, result.backdrop_path, result.id)
                        }
                    } ?: listOf()

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

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                Log.d("myTag", "This is my message")
            }
        })
    }
}