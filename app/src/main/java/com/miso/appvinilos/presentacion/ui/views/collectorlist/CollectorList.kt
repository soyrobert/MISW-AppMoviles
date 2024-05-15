package com.miso.appvinilos.presentacion.ui.views.collectorlist

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel

@Composable
fun CollectorList(viewModel: CollectorViewModel, navigationController: NavHostController) {
    val collectors by viewModel.collectors.observeAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(collectors) { collector ->
                Box(modifier = Modifier.fillMaxSize().clickable {
                    navigationController.navigate("CollectorCompleteDetail/" + collector.id)
                }) {
                    CollectorItem(collector)
                }
            }
        },
        modifier = Modifier.testTag("CollectorList")
    )
}
