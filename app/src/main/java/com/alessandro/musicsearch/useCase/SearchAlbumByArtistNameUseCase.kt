package com.alessandro.musicsearch.useCase

import com.alessandro.musicsearchapi.network.IMusicSearchService
import com.alessandro.musicsearchapi.network.model.AlbumDto
import com.alessandro.musicsearchapi.network.utils.Result
import com.alessandro.musicsearchapi.network.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchAlbumByArtistNameUseCase(private val musicSearchService: IMusicSearchService) :
    ISearchAlbumByArtistNameUseCase {
    override suspend fun execute(searchQuery: String): Result<List<AlbumDto>> {
        return withContext(Dispatchers.IO) {
            try {
                Result(
                    Status.SUCCESS,
                    musicSearchService.searchAlbumByArtistName(searchQuery).body()?.albums,
                    null
                )
            } catch (exception: Exception) {
                Result(Status.ERROR, null, exception.message ?: "Error occured")
            }
        }
    }
}