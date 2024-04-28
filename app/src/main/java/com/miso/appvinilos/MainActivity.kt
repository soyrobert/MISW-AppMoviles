package com.miso.appvinilos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.albums.model.Album
import com.miso.appvinilos.albums.ui.theme.AppVinilosTheme
import com.miso.appvinilos.albums.ui.views.AlbumCompleteDetail
import com.miso.appvinilos.albums.ui.views.AlbumList
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel


class MainActivity : ComponentActivity() {
    private val viewModel: AlbumViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            AppVinilosTheme {
                val navigationController= rememberNavController()
                NavHost(navController = navigationController, startDestination = "AlbumListScreen"){
                    composable("AlbumListScreen"){ AlbumListScreen(navigationController)}
                    composable("AlbumCompleteDetail/{albumId}"){ backStackEntry ->
                        var albumId=backStackEntry.arguments?.getString("albumId")
                        //var albumEjemplo = Album(1, "Album de ejemplo", "cover", "releaseDate","descr","genre","recordlab")
                        var albumIdInt= albumId?.toInt()?:0
                        AlbumCompleteDetail(albumIdInt,navigationController)
                    }
                }
                
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Vinilos App") },
                actions = {
                    Text(
                        text = "Ver Álbumes",
                        modifier = Modifier
                            .padding(horizontal = 1.dp)
                            .clickable(onClick = { navController.navigate("albumList") }),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
            composable("home") { MainScreen() }
            //composable("albumList") { AlbumListScreen(navigationController) }
        }
    }
}



@Composable
fun AlbumScreen(viewModel: AlbumViewModel) {
    val album by viewModel.album.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAlbum(1)
    }

    Column {
        if (album == null) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            // Display the credit card details
//            Text(text = album?.albumId ?: "Unknown")
            Text(text = album?.name ?: "Unknown")
            Text(text = album?.description ?: "Unknown")
        }
    }

}


@Composable
fun AlbumListScreen(navigationController: NavHostController) {
    val viewModel: AlbumViewModel = viewModel()
    LaunchedEffect(key1 = true) {
        viewModel.fetchAlbums()
    }
    Log.d("AlbumListScreen", "Loading albums")
    AlbumList(viewModel,navigationController)
}
