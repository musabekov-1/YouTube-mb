package com.example.youtube.di

import com.example.youtube.ui.main.MainViewModel
import com.example.youtube.ui.playlists.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }
}