package com.vt.teravin_test.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val posterPath: String? = null,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int,
) : Parcelable