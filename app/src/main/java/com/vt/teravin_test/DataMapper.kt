package com.vt.teravin_test

import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.source.local.entity.MovieEntity
import com.vt.teravin_test.core.source.remote.response.MovieItem

object DataMapper {
    fun mapResponseToEntity(input: List<MovieItem>): List<MovieEntity> {
        val movieEntity = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount
            )
            movieEntity.add(movie)
        }
        return movieEntity
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath ?: "",
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
            )
        }
}