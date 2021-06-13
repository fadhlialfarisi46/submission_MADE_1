package com.example.sub1made.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sub1made.R
import com.example.sub1made.core.domain.model.Movie
import com.example.sub1made.core.ui.ViewModelFactory
import com.example.sub1made.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailMovieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)

    }

    private fun showDetailMovie(detailMovie: Movie?) {
        val imgUrl = getString(R.string.baseUrlImage)
        detailMovie?.let {
            val image = buildString {
                append(imgUrl)
                append(detailMovie.posterPath)
            }
            supportActionBar?.title = detailMovie.title
            with(binding.detailContent){
                textTitle.text = detailMovie.title
                textDescription.text = detailMovie.overview
                textRelease.text = detailMovie.releaseDate
                textPopularity.text = detailMovie.popularity.toString()

            }
            Glide.with(this)
                .load(image)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.detailContent.imageDetail)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.detailContent.imgBtnFav.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.detailContent.imgBtnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_true))
        } else {
            binding.detailContent.imgBtnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_false))
        }
    }
}