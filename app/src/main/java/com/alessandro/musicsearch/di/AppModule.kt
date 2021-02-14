package com.alessandro.musicsearch.di

import com.alessandro.musicsearch.search.SearchViewModel
import com.alessandro.musicsearch.useCase.ISearchAlbumByArtistNameUseCase
import com.alessandro.musicsearch.useCase.SearchAlbumByArtistNameUseCase
import com.alessandro.musicsearchapi.network.IMusicSearchService
import com.alessandro.musicsearchapi.network.retrofit.RetrofitBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Application modules injected into app
 */
val appModule: Module = module {
    single {
        RetrofitBuilder(
            baseUrl = "https://itunes.apple.com/"
        ).build()
            .create(IMusicSearchService::class.java)
    }

    factory<ISearchAlbumByArtistNameUseCase> {
        SearchAlbumByArtistNameUseCase(
            musicSearchService = get()
        )
    }

    viewModel {
        SearchViewModel(
            searchAlbumByArtistNameUseCase = get()
        )
    }

}