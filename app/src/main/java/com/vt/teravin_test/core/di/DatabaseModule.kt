package com.vt.teravin_test.core.di

import android.content.Context
import androidx.room.Room
import com.vt.teravin_test.core.source.local.room.MovieDao
import com.vt.teravin_test.core.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context, MovieDatabase::class.java, "TeravinMovies.db"
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}