package com.example.animeexplorer.data.remote

import com.example.animeexplorer.data.model.AnimeDetailResponse
import com.example.animeexplorer.data.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApiService {
    @GET("anime")
    suspend fun getAnimeList(): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: String): AnimeDetailResponse
}