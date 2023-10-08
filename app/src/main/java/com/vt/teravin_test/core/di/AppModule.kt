package com.vt.teravin_test.core.di

import com.vt.teravin_test.core.domain.usecase.IMovieUsecase
import com.vt.teravin_test.core.domain.usecase.MovieInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideMovieUsecase(movieInteractor: MovieInteractor): IMovieUsecase
}