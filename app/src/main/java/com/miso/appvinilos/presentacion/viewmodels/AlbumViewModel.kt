package com.miso.appvinilos.presentacion.viewmodels
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Comment
import com.miso.appvinilos.data.repositories.AlbumRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {
    private val albumRepository = AlbumRepository(application.applicationContext)
    private val _albums = MutableLiveData<List<Album>>()
    private val _albumCreationError = MutableLiveData<Boolean>()
    val albumCreationError: LiveData<Boolean> get() = _albumCreationError

    val albums: LiveData<List<Album>>
        get() = _albums

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album

    fun fetchAlbums(albumsTest:List<Album> = emptyList()){
        viewModelScope.launch {
            try {
                if(albumsTest.isEmpty()){
                    val albums = albumRepository.getAlbums()
                    Log.d("AlbumViewModel", "Fetched albums: ${albums.joinToString { it.name }}")
                    _albums.value = albums}
                else{
                    Log.d("AlbumViewModel", "Fetched test albums: ${albumsTest.joinToString { it.name }}")
                    _albums.value = albumsTest
                }

            } catch (e: Exception) {
                // Handle error
                Log.e("fetchAlbumsError", "Error fetching album details", e)
                e.printStackTrace()
            }
        }
    }


    fun fetchAlbum(albumId: Int) {
        viewModelScope.launch {
            try {
                val foundAlbum = albumRepository.getAlbum(albumId)
                Log.d("fetchAlbum", "fetchAlbum: $foundAlbum")
                _album.value = foundAlbum
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

//    fun createAlbum(album: Album) {
//        viewModelScope.launch {
//            try {
//                val response: Response<Album> = albumRepository.postAlbum(album)
//                if (response.isSuccessful) {
//                    Log.d("createAlbum", "CreateAlbum: ${response.body()}")
//                    _albumCreationError.value = false
//                } else {
//                    Log.d("createAlbum", "Failed with status code: ${response.code()}")
//                    _albumCreationError.value = true
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _albumCreationError.value = true
//            }
//        }
//    }
    private val _postAlbumResponse = MutableLiveData<Response<Album>>()
    val postAlbumResponse: LiveData<Response<Album>> get() = _postAlbumResponse
    fun createAlbum(album: Album) {
        viewModelScope.launch {
            try {
                val response: Response<Album> = albumRepository.postAlbum(album)
                _postAlbumResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("AlbumViewModel", "Error posting comment", e)
            }
        }
    }

//    fun createAlbum(album: Album) {
//        viewModelScope.launch {
//            try {
//                val call = albumRepository.postAlbum(album)
//                call.enqueue(object : Callback<Album> {
//                    override fun onResponse(call: Call<Album>, response: Response<Album>) {
//                        if (response.isSuccessful) {
//                            Log.d("createAlbum", "CreateAlbum: ${response.body()}")
//                            _albumCreationError.value = false
//                        } else {
//                            Log.d("createAlbum", "Failed with status code: ${response.code()}")
//                            _albumCreationError.value = true
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Album>, t: Throwable) {
//                        Log.e("createAlbum", "Error creating album", t)
//                        _albumCreationError.value = true
//                    }
//                })
//            } catch (e: Exception) {
//                Log.e("createAlbum", "Error creating album", e)
//                _albumCreationError.value = true
//            }
//        }
//    }

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    fun fetchComments(albumId: Int) {
        viewModelScope.launch {
            try {
                val comments = albumRepository.getComments(albumId)
                _comments.value = comments
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postComment(albumId: Int, comment: Comment) {
        viewModelScope.launch {
            try {
                albumRepository.postComment(albumId, comment)
                fetchComments(albumId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}