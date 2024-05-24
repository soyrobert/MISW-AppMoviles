package com.miso.appvinilos.data.repositories
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.data.model.toAlbumPostDTO
import com.miso.appvinilos.data.model.CommentRequest
import com.miso.appvinilos.data.network.AlbumsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AlbumRepository(context: Context) {

    @RequiresApi(Build.VERSION_CODES.M)
    private val albumsApi=AlbumsApi(context)
    @RequiresApi(Build.VERSION_CODES.M)
    private val albumService = albumsApi.albumsService

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getAlbum(albumId: Int): Album {
        return albumService.getAlbum(albumId)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun postAlbum(album: Album): Response<Album> {
        val albumPostDTO = album.toAlbumPostDTO()
        return withContext(Dispatchers.IO) {
            albumService.postAlbum(albumPostDTO)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getComments(albumId: Int): List<Comment> {
        Log.d("AlbumRepository", "Fetching comments for albumId: $albumId")
        return albumService.getComments(albumId)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun postComment(albumId: Int, commentRequest: CommentRequest): Response<Comment> {
        Log.d("AlbumRepository", "Posting comment: $commentRequest to albumId: $albumId")
        return withContext(Dispatchers.IO) {
            albumService.postComment(albumId, commentRequest)
        }
    }
}