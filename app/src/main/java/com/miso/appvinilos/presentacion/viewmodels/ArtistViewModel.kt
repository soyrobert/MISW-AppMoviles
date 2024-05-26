package com.miso.appvinilos.presentacion.viewmodels
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistViewModel(application: Application) : AndroidViewModel(application) {
    private val artistRepository = ArtistRepository(application)
    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>>
        get() = _artists

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist>
        get() = _artist

    @RequiresApi(Build.VERSION_CODES.M)
    fun fetchArtists(artistsTest: List<Artist> = emptyList()) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (artistsTest.isEmpty()) {
                    val artists = artistRepository.getArtists()
                    Log.d("ArtistViewModel", "Fetched artists: ${artists.joinToString { it.name }}")

                    withContext(Dispatchers.Main) {
                        _artists.value = artists
                    }
                } else {
                    Log.d(
                        "ArtistViewModel",
                        "Fetched test artists: ${artistsTest.joinToString { it.name }}"
                    )

                    withContext(Dispatchers.Main) {
                        _artists.value = artistsTest
                    }
                }
            } catch (e: Exception) {
                Log.e("fetchArtistsError", "Error fetching artists details", e)
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun fetchArtist(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val foundArtist = artistRepository.getArtist(id)
                Log.d("fetchArtist", "fetchArtist: $foundArtist")

                withContext(Dispatchers.Main) {
                    _artist.value = foundArtist
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}