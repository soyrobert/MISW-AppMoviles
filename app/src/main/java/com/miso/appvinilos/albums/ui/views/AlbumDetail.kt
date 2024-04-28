package com.miso.appvinilos.albums.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.albums.model.Album
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AlbumCompleteDetail(albumId: Int, navigationController: NavHostController) {
    val viewModel: AlbumViewModel = viewModel()

    val initialAlbum = Album(0, albumId.toString(), "cover", "releaseDate",
        "descr","genre","record lab")

    LaunchedEffect(key1 = true) {
        viewModel.fetchAlbum(albumId)
    }


    val albumToShow by viewModel.album.observeAsState(initial = initialAlbum)

    Column {
        Header(navigationController)
        AlbumDetail(albumToShow)
        AlbumDescription(albumToShow)
    }
}


@Composable
fun TopBar(navigationController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navigationController.navigate("AlbumListScreen")}) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
        }

        Title()
    }
}


@Composable
fun Title() {
    Text(
        text = "Álbum",
        style = TextStyle(
            color = Color(0xFF1B1C17),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
        ),
        modifier = Modifier.fillMaxWidth(0.8f)
    )

}


@Composable
fun Header(navigationController: NavHostController) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopBar(navigationController)
        }
    }
}


@Composable
fun AlbumPhotoScreen(cover: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .background(
                color = Color.White,
            )
            .padding(1.dp)
    ) {
        GlideImage(
            imageModel = { cover },

            modifier = Modifier
                .height(185.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
    }
}


@Composable
fun AlbumBasicDescription(album: Album){
    GenreScreenField(album)
    DiscographyScreenField(album)
    PublicationDateScreenField(album)
}

@Composable
fun DiscographyScreenField(album: Album) {
    Column {
        LightText(text = "Discografía")
        DarkText(text = album.recordLabel)
    }
}

@Composable
fun PublicationDateScreenField(album: Album) {
    Column {
        LightText(text = "Fecha publicación")
        DarkText(text = album.releaseDate)
    }
}

@Composable
fun LightText(text: String){
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(300),
            color = Color(0xFF605D66),
        )
    )
}

@Composable
fun DarkText(text: String){
    Text(
        text = text,
        style = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(300),
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    )
}

@Composable
fun CustomParagraph(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight(400),
            color = Color(0xFF45483C),
            letterSpacing = 0.4.sp,
        )
    )
}

@Composable
fun GenreScreenField(album: Album){
    Column {
        LightText(text = "Género")
        DarkText(text = album.genre)
    }
}


@Composable
fun AlbumDetail(album: Album) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.White
            )
            .background(
                color = Color.White,
            )
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
            ) {
                AlbumPhotoScreen(album.cover)
            }
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                AlbumBasicDescription(album)
            }

        }

    }
}

@Composable
fun AlbumDescription(album: Album) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.White
            )
            .background(
                color = Color.White,
            )
            .padding(5.dp)
    ) {
        Column {
            Row {
                DarkText(text = album.name)
            }
            CustomWhiteSpace()
            Row {
                CustomParagraph(text = album.description)
            }
        }
    }

}

@Composable
fun CustomWhiteSpace(){
    Surface (
        Modifier
            .height(20.dp)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 96.dp)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {}
    }
}