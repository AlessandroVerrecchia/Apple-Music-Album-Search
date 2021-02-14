package com.alessandro.musicsearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alessandro.musicsearch.R
import com.alessandro.musicsearch.search.IAlbumClickListener
import com.alessandro.musicsearchapi.network.model.AlbumDto

class AlbumsAdapter(private val albumClickListener: IAlbumClickListener) :
    RecyclerView.Adapter<AlbumItemViewHolder>() {

    private var albums = listOf<AlbumDto>()

    fun updateList(albums: List<AlbumDto>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        return AlbumItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        val album = albums[position]
        holder.apply {
            container.setOnClickListener {
                albumClickListener.onClick(album)
            }
            albumName.text = album.name
            albumReleaseDate.text = album.releaseDate.take(10)
            loadImage(albumArtwork, album.artworkUrl)
        }
    }

    override fun getItemCount(): Int = albums.size
}