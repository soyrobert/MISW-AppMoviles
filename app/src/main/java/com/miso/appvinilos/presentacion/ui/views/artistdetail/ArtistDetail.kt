package com.miso.appvinilos.presentacion.ui.views.artistdetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.presentacion.ui.views.utils.Header
import com.miso.appvinilos.presentacion.viewmodels.ArtistViewModel
import com.skydoves.landscapist.glide.GlideImage
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ArtistCompleteDetail(artistId: Int, navigationController: NavHostController, artistTest: List<Artist> = emptyList()) {
    val viewModel: ArtistViewModel = viewModel()

    val initialArtist = Artist(
        id = 0,
        name = "",
        image = "",
        description = "",
        birthDate = "",
        albums = emptyList()
    )

    LaunchedEffect(key1 = true) {
        viewModel.fetchArtist(artistId)
    }

    val artistToShow by viewModel.artist.observeAsState(initial = initialArtist)

    if (artistTest.isNotEmpty()) {
        val artistTest = artistTest[artistId - 1]
        ArtistDetailContent(artist = artistTest, navigationController = navigationController)
    } else {
        ArtistDetailContent(artist = artistToShow, navigationController = navigationController)
    }
}

@Composable
fun ArtistDetailContent(artist: Artist, navigationController: NavHostController) {
    val formattedDate = formatDate(artist.birthDate)

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        item {
            Header(text = "Artista", navigationController)
            Spacer(modifier = Modifier.height(8.dp))
            ArtistPhotoScreen(artist.image)
            Spacer(modifier = Modifier.height(35.dp))
            TitleText(text = artist.name)
            Spacer(modifier = Modifier.height(15.dp))
        }
        item {
            Row {
                Spacer(modifier = Modifier.padding(8.dp, 0.dp, 16.dp, 0.dp))
                DarkText(text = "Nacimiento: ")
                LightText(text = formattedDate)
            }
            Spacer(modifier = Modifier.height(15.dp))
            CustomParagraph(text = artist.description)
            Spacer(modifier = Modifier.height(15.dp))
        }
        item {
            Text(
                text = "Álbumes",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(artist.albums.chunked(3)) { rowAlbums ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowAlbums.forEach { album ->
                    AlbumItem(album = album, Modifier.weight(1f))
                }

                repeat(3 - rowAlbums.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun AlbumItem(album: Album, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        GlideImage(
            imageModel = { album.cover },
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = album.name,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Text(
            text = album.genre,
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
    }
}

@Composable
fun ArtistPhotoScreen(cover: String) {
    Box(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        GlideImage(
            imageModel = { cover },
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(8.dp))
                .semantics { contentDescription = "Foto del artista" }
        )
    }
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(700),
            color = Color(0xFF1B1C17)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun DarkText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(300),
            fontFamily = FontFamily.Serif,
            fontSize = 14.sp,
            lineHeight = 24.sp
        )
    )
}

@Composable
fun LightText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(300),
            color = Color(0xFF605D66)
        )
    )
}

@Composable
fun CustomParagraph(text: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight(400),
            color = Color(0xFF45483C),
            letterSpacing = 0.4.sp
        )
    )
}

fun formatDate(dateString: String): String {
    return try {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date = inputFormatter.parse(dateString)
        outputFormatter.format(date)
    } catch (e: ParseException) {
        dateString
    }
}
