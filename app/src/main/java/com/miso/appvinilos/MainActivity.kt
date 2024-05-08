package com.miso.appvinilos
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.albums.ui.theme.AppVinilosTheme
import com.miso.appvinilos.albums.ui.views.AlbumCompleteDetail
import com.miso.appvinilos.albums.ui.views.AlbumList
import com.miso.appvinilos.albums.viewmodels.AlbumViewModel
import com.miso.appvinilos.collectors.ui.views.CollectorList
import com.miso.appvinilos.collectors.viewmodels.CollectorViewModel
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.ui.views.artistdetail.ArtistCompleteDetail
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.presentacion.ui.views.artistlist.ArtistListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            AppVinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            Navigations(navController = navController)
        }
    }
}

sealed class NavigationItem(var route: String, val title: String, val icon: Int) {
    object Albums : NavigationItem("Albums", "IconoAlbums", R.drawable.ic_album)
    object Artist : NavigationItem("Artist", "IconoArtist", R.drawable.ic_artist)
    object Collector : NavigationItem("Collector", "IconoCollector", R.drawable.ic_collector)
    object Home : NavigationItem("Home", "IconoHome", R.drawable.ic_home)
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Albums,
        NavigationItem.Artist,
        NavigationItem.Collector,
        NavigationItem.Home
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.Home.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                modifier = Modifier.testTag(item.title),
                icon = {
                    val imagePainter = painterResource(id = item.icon)
                    Image(
                        painter = imagePainter,
                        contentDescription = null // Provide content description if needed
                    )
                },
                label = { Text(item.title) },
                //selected = selectedItem == index,
                selected = false,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        var launchSingleTop = true
                        var restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Albums.route) {
            AlbumListScreen(navController)}
            composable("AlbumCompleteDetail/{albumId}"){ backStackEntry ->

                val albumId=backStackEntry.arguments?.getString("albumId")
                val albumIdInt= albumId?.toInt()?:0

                AlbumCompleteDetail(albumIdInt, navController)
        }
        composable(NavigationItem.Artist.route) {
            ArtistListScreen(navController)}
            composable("ArtistCompleteDetail/{artistId}"){ backStackEntry ->

                val artistId=backStackEntry.arguments?.getString("artistId")
                val artistIdInt= artistId?.toInt()?:0

                ArtistCompleteDetail(artistIdInt, navController)
        }
        composable(NavigationItem.Collector.route) {
            CollectorListScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
    }
}

@Composable
fun CenterText(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, fontSize = 32.sp)
    }
}



@Composable
fun CollectorScreen(navigationController: NavHostController) {
    CenterText(text = "Collectors")
}

@Composable
fun HomeScreen(navigationController: NavHostController) {
    CenterText(text = "Home")
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

@Composable
fun CollectorListScreen(navigationController: NavHostController,collectorsTest:List<Collector> = emptyList()) {

    val viewModel: CollectorViewModel = viewModel()

    if (collectorsTest.isNotEmpty()) {
        LaunchedEffect(key1 = true) {
            viewModel.fetchCollectors(collectorsTest)
        }
    }
    else {
        LaunchedEffect(key1 = true) {
            viewModel.fetchCollectors()
        }
    }



    CollectorList(viewModel,navigationController)
}
