package com.miso.appvinilos.data.repositories
import android.content.Context
import com.miso.appvinilos.MainActivity
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.network.AlbumsApi

class AlbumRepository(context: Context) {

    private val albumsApi=AlbumsApi(context)
    private val albumService = albumsApi.albumsService

    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return albumService.getAlbum(albumId)
    }
}