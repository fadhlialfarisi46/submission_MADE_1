package com.example.sub1made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val id: Int,
    val isFavorite: Boolean
):Parcelable
