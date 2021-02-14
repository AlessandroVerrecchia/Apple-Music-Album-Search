package com.alessandro.musicsearch.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumItemViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val container: ConstraintLayout = itemView.container
    val albumName: TextView = itemView.albumNameTextView
    val albumReleaseDate: TextView = itemView.albumReleaseDateTextView
    val albumArtwork: ImageView = itemView.albumArtworkImageView
}