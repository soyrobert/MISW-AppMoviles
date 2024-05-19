package com.miso.appvinilos.data.repositories
import android.content.Context
import android.util.Log
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.data.model.CommentRequest
import com.miso.appvinilos.data.network.AlbumsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AlbumRepository(context: Context) {

    private val albumsApi=AlbumsApi(context)
    private val albumService = albumsApi.albumsService

    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return albumService.getAlbum(albumId)
    }

    suspend fun postAlbum(album: Album) =
        albumService.postAlbum(album)

    suspend fun getComments(albumId: Int): List<Comment> {
        Log.d("AlbumRepository", "Fetching comments for albumId: $albumId")
        return albumService.getComments(albumId)
    }

    suspend fun postComment(albumId: Int, commentRequest: CommentRequest): Response<Comment> {
        Log.d("AlbumRepository", "Posting comment: $commentRequest to albumId: $albumId")
        return withContext(Dispatchers.IO) {
            albumService.postComment(albumId, commentRequest)
        }
    }
}