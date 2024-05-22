package com.miso.appvinilos.data.repositories
import android.content.Context
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.network.ArtistsApi

class ArtistRepository(context: Context) {
    private val artistApi = ArtistsApi(context)
    private val artistService = artistApi.artistService

    suspend fun getArtists(): List<Artist> {
        return artistService.getArtists()
    }

    suspend fun getArtist(id: Int): Artist {
        return artistService.getArtist(id)
    }
}