package com.miso.appvinilos.data.repositories
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.network.ArtistsApi

class ArtistRepository(context: Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val artistApi = ArtistsApi(context)
    @RequiresApi(Build.VERSION_CODES.M)
    private val artistService = artistApi.artistService

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getArtists(): List<Artist> {
        return artistService.getArtists()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getArtist(id: Int): Artist {
        return artistService.getArtist(id)
    }
}