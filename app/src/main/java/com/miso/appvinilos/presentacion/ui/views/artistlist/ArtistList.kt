package com.miso.appvinilos.presentacion.ui.views.artistlist
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.presentacion.viewmodels.ArtistViewModel
import com.miso.appvinilos.data.model.Artist
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.skydoves.landscapist.glide.GlideImage

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ArtistListScreen(navigationController: NavHostController,artistTest:List<Artist> = emptyList()) {
    val viewModel: ArtistViewModel = viewModel()

    if (artistTest.isNotEmpty()) {
        LaunchedEffect(key1 = true) {
            viewModel.fetchArtists(artistTest)
        }
    }
    else {
        LaunchedEffect(key1 = true) {
            viewModel.fetchArtists()
        }
    }

    //CenterText(text = "Artist")
    ArtistList(viewModel,navigationController)
}

@Composable
fun ArtistList(viewModel: ArtistViewModel, navigationController: NavHostController) {
    val artists by viewModel.artists.observeAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(artists) { artist ->

                Box(modifier=Modifier.fillMaxSize().clickable {
                    navigationController.navigate("ArtistCompleteDetail/" + artist.id)
                }){
                    ArtistItem(artist)
                }

            }
        },
        modifier= Modifier.testTag("ArtistList")
    )
}

@Composable
fun ArtistItem(artist: Artist) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(176.dp)
            .clipToBounds()
            .semantics(mergeDescendants = true){}
    ) {
        GlideImage(
            imageModel = { artist.image },

            modifier = Modifier
                .height(185.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .semantics {
                    contentDescription = "Imagen del artista llamado " + artist.name
                }
        )
        Text(
            text = artist.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}