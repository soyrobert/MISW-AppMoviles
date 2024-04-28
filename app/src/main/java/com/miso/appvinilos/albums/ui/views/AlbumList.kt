package com.miso.appvinilos.albums.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel


@Composable
fun AlbumList(viewModel: AlbumViewModel, navigationController: NavHostController) {
    val albums by viewModel.albums.observeAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(albums) { album ->
                Box(modifier=Modifier.fillMaxSize().clickable {
                    navigationController.navigate("AlbumCompleteDetail/" + album.id)
                }){
                    AlbumItem(album)
                }

            }
        }
    )
}