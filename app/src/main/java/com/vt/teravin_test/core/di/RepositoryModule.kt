package com.vt.teravin_test.core.di

import com.vt.teravin_test.core.domain.repository.IMovieRepository
import com.vt.teravin_test.core.source.TeravinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(teravinRepository: TeravinRepository): IMovieRepository
}