package com.miso.appvinilos.presentacion.ui.views.artistdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.presentacion.viewmodels.ArtistViewModel
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ArtistCompleteDetail(artistId: Int, navigationController: NavHostController, artistTest:List<Artist> = emptyList()) {
    val viewModel: ArtistViewModel = viewModel()


    val initialArtist = Artist(0, artistId.toString(), "name", "descr", "birthday")
    LaunchedEffect(key1 = true) {
        viewModel.fetchArtist(artistId)
    }

    val artistToShow by viewModel.artist.observeAsState(initial = initialArtist)

    if(artistTest.isNotEmpty()){
        val artistTest = artistTest[artistId-1]
        ArtistBasicDetail(artistTest, navigationController)
    }
    else{
        ArtistBasicDetail(artistToShow, navigationController)
    }

}

@Composable
fun ArtistBasicDetail(artist: Artist, navigationController: NavHostController){
    Column(modifier = Modifier.padding(5.dp)) {
        Header(navigationController)
        Spacer(modifier = Modifier.height(2.dp))
        ArtistPhotoScreen(artist.image)
        Spacer(modifier = Modifier.height(35.dp))
        TitleText(text = artist.name)
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            DarkText(text = "Nacimiento: ")
            LightText(text = artist.birthDate)
        }
        Spacer(modifier = Modifier.height(15.dp))
        CustomParagraph(text = artist.description)
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

        IconButton(onClick = { navigationController.popBackStack()},modifier= Modifier.testTag("backButton")) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
        }
        Title()
    }
}


@Preview
@Composable
fun Title() {
    Text(
        text = "Artista",
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
fun ArtistPhotoScreen(cover: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(1.dp)
            .aspectRatio(16f/9f) // Maintain aspect ratio 16:9
            .requiredWidthIn(max = 300.dp) // Set maximum width
            .requiredHeightIn(max = 270.dp) // Set maximum height
    ) {
        GlideImage(
            imageModel = { cover },
            modifier = Modifier
                .fillMaxSize() // Ensure the image fills the Box
                .clip(shape = RoundedCornerShape(8.dp))
        )
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
fun TitleText(text: String){
    Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(300),
            color = Color(0xFF605D66),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // optional padding
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
            fontSize = 14.sp,
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