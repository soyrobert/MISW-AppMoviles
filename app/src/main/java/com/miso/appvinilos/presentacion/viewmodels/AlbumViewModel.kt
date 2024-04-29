package com.miso.appvinilos.albums.viewmodels
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.repositories.AlbumRepository
import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import android.util.Log

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {
    private val albumRepository = AlbumRepository()
    private val _albums = MutableLiveData<List<Album>>()
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

}