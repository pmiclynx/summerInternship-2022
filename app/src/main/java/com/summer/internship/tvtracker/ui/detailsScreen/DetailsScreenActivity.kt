package com.summer.internship.tvtracker.ui.detailsScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.databinding.ActivityDetailsScreenBinding
import com.summer.internship.tvtracker.di.RoomModule
import com.summer.internship.tvtracker.domain.details.OnAddListener
import com.summer.internship.tvtracker.ui.GlideImageLoader
import com.summer.internship.tvtracker.ui.MainActivity

class DetailsScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsScreenBinding
    private lateinit var details: TvDetailsResponse
    val model: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        val id = extras?.getLong("id")
        Log.i("aaaaaaa", id.toString())


        model.getDetails().observe(this, Observer<TvDetailsResponse> { details ->
            Log.i("aaaaaaa", details.overView)
            binding.textviewOverview.text = details.overView
            binding.textViewMovieRating.text = details.voteAverage.toString()
            binding.textViewMovieName.text = details.name
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
            this.details = details

        })
        model.isAddSuccessful.observe(this) {
            Toast.makeText(
                this@DetailsScreenActivity,
                "Added to Favorites",
                Toast.LENGTH_SHORT
            ).show()
        }
        model.isAddFailed.observe(this) {
            Toast.makeText(
                this@DetailsScreenActivity,
                "Error or movie already exists",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.buttonFavorite.setOnClickListener {
            if (this::details.isInitialized) {
                model.addFavorite(details, id)
            }
        }
        id?.let {
            model.loadDetails(id)
        }

    }

}