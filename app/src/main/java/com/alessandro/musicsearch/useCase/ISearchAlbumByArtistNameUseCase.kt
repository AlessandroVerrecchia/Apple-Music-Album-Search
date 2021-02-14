package com.alessandro.musicsearch.useCase

import com.alessandro.musicsearchapi.network.model.AlbumDto
import com.alessandro.musicsearchapi.network.utils.Result

interface ISearchAlbumByArtistNameUseCase {
    suspend fun execute(searchQuery: String): Result<List<AlbumDto>>
}