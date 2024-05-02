package com.miso.appvinilos.data.repositories
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.network.ArtistsApi

class ArtistRepository {
    private val artistService = ArtistsApi.artistService

    suspend fun getArtists(): List<Artist> {
        return artistService.getArtists()
    }

    suspend fun getArtist(id: Int): Artist {
        return artistService.getArtist(id)
    }
}