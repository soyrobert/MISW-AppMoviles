package com.miso.appvinilos.albums.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel


@Composable
fun AlbumList(viewModel: AlbumViewModel) {
    val albums by viewModel.albums.observeAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(albums) { album ->
                AlbumItem(album)
            }
        },
        modifier= Modifier.testTag("AlbumList")
    )
}