package com.vlad.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemPreviewNews(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    @SerializedName("urlToImage")
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,
    ): Parcelable

@Parcelize
data class Source(
    val id: String?,
    val name: String?
): Parcelable