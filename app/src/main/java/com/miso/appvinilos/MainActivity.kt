package com.miso.appvinilos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.albums.ui.theme.AppVinilosTheme
import com.miso.appvinilos.albums.ui.views.AlbumCompleteDetail
import com.miso.appvinilos.albums.ui.views.AlbumList
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            AppVinilosTheme {
                val navigationController= rememberNavController()
                NavHost(navController = navigationController, startDestination = "AlbumListScreen"){
                    composable("AlbumListScreen"){ AlbumListScreen(navigationController)}
                    composable("AlbumCompleteDetail/{albumId}"){ backStackEntry ->

                        val albumId=backStackEntry.arguments?.getString("albumId")
                        val albumIdInt= albumId?.toInt()?:0

                        AlbumCompleteDetail(albumIdInt,navigationController)
                    }
                }
                
            }
        }
    }
}



@Composable
fun AlbumListScreen(navigationController: NavHostController,albumsTest:List<Album> = emptyList()) {

    val viewModel: AlbumViewModel = viewModel()

    if (albumsTest.isNotEmpty()) {
        LaunchedEffect(key1 = true) {
            viewModel.fetchAlbums(albumsTest)
        }
    }
    else {
        LaunchedEffect(key1 = true) {
            viewModel.fetchAlbums()
        }
    }


    Log.d("AlbumListScreen", "Loading albums")
    AlbumList(viewModel,navigationController)
}
