package com.alessandro.musicsearchapi.network

import com.alessandro.musicsearchapi.network.model.AlbumContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMusicSearchService {

    @GET("search")
    suspend fun searchAlbumByArtistName(
        @Query("term")
        query: String,
        @Query("media")
        media: String = "music",
        @Query("entity")
        entity: String = "album",
        @Query("attribute")
        attribute: String = "artistTerm",
        @Query("country")
        country: String = "CA"
    ): Response<AlbumContainer>
}