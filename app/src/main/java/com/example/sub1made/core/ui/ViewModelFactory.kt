package com.example.sub1made.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sub1made.core.di.Injection
import com.example.sub1made.core.domain.usecase.MovieUseCase
import com.example.sub1made.detail.DetailMovieViewModel
import com.example.sub1made.favorite.FavoriteMovieViewModel
import com.example.sub1made.home.MenuViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase):
    ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideMovieUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(MenuViewModel::class.java) -> {
                MenuViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class:" + modelClass.name)
        }

}