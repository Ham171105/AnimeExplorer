package com.example.animeexplorer.data.model

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val data: List<Anime>
)

data class AnimeDetailResponse(
    @SerializedName("data")
    val data: Anime
)

data class Anime(
    @SerializedName("id", alternate = ["mal_id"])
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("score")
    val score: Double?,

    @SerializedName("year")
    val releaseYear: Int?,

    @SerializedName("episodes")
    val episodes: Int?,

    @SerializedName("rating")
    val ageRating: String? = null,

    @SerializedName("synopsis")
    val synopsis: String? = null
)
