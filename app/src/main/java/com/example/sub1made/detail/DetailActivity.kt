package com.example.sub1made.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sub1made.R
import com.example.sub1made.core.domain.model.Movie
import com.example.sub1made.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

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
            val imageBanner = buildString {
                append(imgUrl)
                append(detailMovie.backdropPath)
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

            Glide.with(this)
                .load(imageBanner)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.detailContent.iamgeBanner)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.detailContent.imgBtnFav.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
                setSnackbar(detailMovie.title, statusFavorite)
            }
        }
    }

    private fun setSnackbar(title: String, statusFavorite: Boolean) {
        if (statusFavorite){
            Snackbar.make(
                binding.root,
                "$title added to favorite",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                binding.root,
                "$title removed from favorite",
                Snackbar.LENGTH_SHORT
            ).show()
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