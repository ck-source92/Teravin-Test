package com.vt.teravin_test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vt.teravin_test.core.domain.usecase.IMovieUsecase
import com.vt.teravin_test.core.source.TeravinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUsecase: IMovieUsecase) :
    ViewModel() {

    val movies = movieUsecase.getAllMovie().asLiveData()
}