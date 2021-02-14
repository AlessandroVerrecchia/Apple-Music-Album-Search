package com.alessandro.musicsearch.search

import com.alessandro.musicsearchapi.network.model.AlbumDto

interface IAlbumClickListener {
    fun onClick(albumDto: AlbumDto)
}