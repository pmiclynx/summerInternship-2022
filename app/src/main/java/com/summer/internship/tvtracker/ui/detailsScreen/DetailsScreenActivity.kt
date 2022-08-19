package com.summer.internship.tvtracker.ui.detailsScreen

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.databinding.ActivityDetailsScreenBinding
import com.summer.internship.tvtracker.ui.GlideImageLoader

class DetailsScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        val id = extras?.getLong("id")
        Log.i("aaaaaaa", id.toString())

        val model: DetailsViewModel by viewModels()

        model.getDetails().observe(this, Observer<TvDetailsResponse> { details ->
            Log.i("aaaaaaa", details.overView)
            binding.textviewOverview.text = details.overView
            binding.textViewMovieRating.text = details.voteAverage.toString()
            binding.textViewMovieName.text=details.name
            GlideImageLoader().loadImage(
                binding.root,
                details.backdropPath,
                binding.imageViewBackgroundMovie
            )
            GlideImageLoader().loadImage(
                binding.root,
                details.posterPath,
                binding.imageViewCoverMovie
            )
        })
        id?.let {
            model.loadDetails(id)
        }

    }
}