package com.alessandro.musicsearch.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alessandro.musicsearch.useCase.ISearchAlbumByArtistNameUseCase
import com.alessandro.musicsearchapi.network.model.AlbumDto
import com.alessandro.musicsearchapi.network.utils.Status
import kotlinx.coroutines.launch

class SearchViewModel(private val searchAlbumByArtistNameUseCase: ISearchAlbumByArtistNameUseCase) :
    ViewModel() {

    val albumsLiveData = MutableLiveData<List<AlbumDto>>()
    val errorLiveData = MutableLiveData<String?>()

    fun searchAlbums(searchQuery: String) {
        viewModelScope.launch {
            val result = searchAlbumByArtistNameUseCase.execute(searchQuery)
            if (result.status == Status.SUCCESS) {
                albumsLiveData.value = result.data
            } else {
                errorLiveData.value = result.message
            }
        }
    }

}