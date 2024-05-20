package com.miso.appvinilos.presentacion.viewmodels
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.repositories.ArtistRepository
import kotlinx.coroutines.launch

class ArtistViewModel(application: Application) : AndroidViewModel(application) {
    private val artistRepository = ArtistRepository(application)
    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>>
        get() = _artists

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist>
        get() = _artist

    fun fetchArtists(artistsTest: List<Artist> = emptyList()) {
        viewModelScope.launch {
            try {
                if (artistsTest.isEmpty()) {
                    val artists = artistRepository.getArtists()
                    Log.d("ArtistViewModel", "Fetched artists: ${artists.joinToString { it.name }}")
                    _artists.value = artists
                } else {
                    Log.d("ArtistViewModel", "Fetched test artists: ${artistsTest.joinToString { it.name }}")
                    _artists.value = artistsTest
                }
            } catch (e: Exception) {
                Log.e("fetchArtistsError", "Error fetching artists details", e)
                e.printStackTrace()
            }
        }
    }

    fun fetchArtist(id: Int) {
        viewModelScope.launch {
            try {
                val foundArtist = artistRepository.getArtist(id)
                Log.d("fetchArtist", "fetchArtist: $foundArtist")
                _artist.value = foundArtist
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}