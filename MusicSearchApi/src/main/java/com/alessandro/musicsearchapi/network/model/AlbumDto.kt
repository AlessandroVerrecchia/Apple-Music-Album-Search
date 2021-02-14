package com.alessandro.musicsearchapi.network.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumContainer(
    @Expose
    @SerializedName("resultCount")
    val count: Int,
    @Expose
    @SerializedName("results")
    val albums: List<AlbumDto>
) : Parcelable

@Parcelize
data class AlbumDto(
    @Expose
    @SerializedName("collectionId")
    val id: String,
    @Expose
    @SerializedName("artworkUrl100")
    val artworkUrl: String,
    @Expose
    @SerializedName("collectionName")
    val name: String,
    @Expose
    @SerializedName("releaseDate")
    val releaseDate: String,
    @Expose
    @SerializedName("primaryGenreName")
    val primaryGenreName: String,
    @Expose
    @SerializedName("collectionPrice")
    val collectionPrice: String,
    @Expose
    @SerializedName("copyright")
    val copyright: String,
    @Expose
    @SerializedName("currency")
    val currency: String
) : Parcelable



