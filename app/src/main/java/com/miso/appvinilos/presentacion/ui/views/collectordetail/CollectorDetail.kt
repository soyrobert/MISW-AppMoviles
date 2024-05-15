package com.miso.appvinilos.presentacion.ui.views.collectordetail

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
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.presentacion.ui.views.artistdetail.ArtistBasicDetail
import com.miso.appvinilos.presentacion.ui.views.artistdetail.ArtistPhotoScreen
import com.miso.appvinilos.presentacion.ui.views.artistdetail.CustomParagraph
import com.miso.appvinilos.presentacion.ui.views.artistdetail.DarkText
import com.miso.appvinilos.presentacion.ui.views.artistdetail.Header
import com.miso.appvinilos.presentacion.ui.views.artistdetail.LightText
import com.miso.appvinilos.presentacion.ui.views.artistdetail.TitleText
import com.miso.appvinilos.presentacion.viewmodels.ArtistViewModel
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun CollectorCompleteDetail(collectorId: Int, navigationController: NavHostController, collectorTest:List<Collector> = emptyList()) {
    val viewModel: CollectorViewModel = viewModel()


    val initialCollectorAlbums = listOf(
        Album(0, "name", "cover", "releaseDate",
        "descr","genre","record lab")
    )
    LaunchedEffect(key1 = true) {
        viewModel.fetchCollectorAlbums(collectorId)
    }

    val collectorAlbumsToShow by viewModel.collectorAlbums.observeAsState(initial = initialCollectorAlbums)

    if(collectorTest.isNotEmpty()){
        Text(text = "prueba")
    }
    else{
        CollectorBasicDetail(collectorAlbumsToShow, navigationController)
    }

}

@Composable
fun CollectorBasicDetail(collectorAlbums: List<Album>, navigationController: NavHostController){
    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = collectorAlbums[0].name)
    }
}