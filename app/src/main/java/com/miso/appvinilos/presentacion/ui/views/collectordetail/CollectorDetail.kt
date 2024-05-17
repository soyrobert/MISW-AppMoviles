package com.miso.appvinilos.presentacion.ui.views.collectordetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.presentacion.ui.views.albumlist.AlbumItem
import com.miso.appvinilos.presentacion.ui.views.utils.Header
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel


@Composable
fun CollectorCompleteDetail(collectorId: Int, navigationController: NavHostController, collectorTest:List<Collector> = emptyList()) {
    val viewModel: CollectorViewModel = viewModel()


    val initialCollectorAlbums = listOf(
        Album(0, "name", "cover", "releaseDate",
        "descr","genre","record lab", emptyList() )
    )
    LaunchedEffect(key1 = true) {
        viewModel.fetchCollectorAlbums(collectorId)
    }

    val collectorAlbumsToShow by viewModel.collectorAlbums.observeAsState(initial = initialCollectorAlbums)

    if(collectorTest.isNotEmpty()){
        Text(text = "prueba")
    }
    else{
        Column {
            Header(text="Coleccionista",navigationController)
            CollectorBasicDetail(collectorAlbumsToShow, navigationController)
        }
    }

}

@Composable
fun CollectorBasicDetail(collectorAlbums: List<Album>, navigationController: NavHostController){
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