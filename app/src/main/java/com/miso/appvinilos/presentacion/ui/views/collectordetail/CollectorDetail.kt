package com.miso.appvinilos.presentacion.ui.views.collectordetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.presentacion.ui.views.albumlist.AlbumItem
import com.miso.appvinilos.presentacion.ui.views.utils.Header
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun CollectorCompleteDetail(collectorId: Int,
                            navigationController: NavHostController,
                            collectorsTest:List<Collector> = emptyList(),
                            collectorAlbumsTest:List<Album> = emptyList()

) {
    val viewModel: CollectorViewModel = viewModel()


    val initialCollectorAlbums = listOf(
        Album(0, "name", "cover", "releaseDate",
        "descr","genre","record lab", emptyList() )
    )
    LaunchedEffect(key1 = true) {
        viewModel.fetchCollectorAlbums(collectorId)
        viewModel.fetchCollector(collectorId)
    }

    val collectorAlbumsToShow by viewModel.collectorAlbums.observeAsState(initial = initialCollectorAlbums)

    val initialColeccionista = Collector(0, "Nombre", "telefono", "email", emptyList(), emptyList(), emptyList())
    val coleccionista by viewModel.collector.observeAsState(initial = initialColeccionista)

    if(collectorsTest.isNotEmpty() && collectorAlbumsTest.isNotEmpty()){
        val collectorTest:Collector = collectorsTest[collectorId-1]
        CollectorBasicDetail(collectorTest,collectorAlbumsTest, navigationController)
    }
    else{
        CollectorBasicDetail(coleccionista,collectorAlbumsToShow, navigationController)
    }

}
@Composable
fun CollectorBasicDetail(coleccionista:Collector,collectorAlbums: List<Album>, navigationController: NavHostController) {
    Column {
        Header(text="Coleccionista",navigationController)
        NombreColeccionista(coleccionista.name)
        SubtituloDetalleColeccionista( "Albumes favoritos")
        CollectorAlbums(collectorAlbums)
    }
}

@Composable
fun CollectorAlbums(collectorAlbums: List<Album>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(collectorAlbums) { album ->

                Box(modifier=Modifier.fillMaxSize()){
                    AlbumItem(album)
                }

            }
        },
        modifier= Modifier.testTag("collectorAlbumsList")
    )
}

@Preview(showBackground = true)
@Composable
fun NombreColeccionista(nombre: String="Nombre"){
    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp)
    ) {
        Text(
            text = nombre,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                color = Color(0xFF1B1C17),
                textAlign = TextAlign.Start,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400)
            )
        )
    }
}



@Preview(showBackground = true)
@Composable
fun SubtituloDetalleColeccionista(subtitulo: String="subtitulo"){
    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp)
    ) {
        Text(
            text = subtitulo,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color(0xFF1B1C17),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400)
            )
        )
    }
}