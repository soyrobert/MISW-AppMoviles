package com.miso.appvinilos

import com.miso.appvinilos.presentacion.ui.views.albumdetail.AddCommentScreen
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.presentacion.ui.theme.AppVinilosTheme
import com.miso.appvinilos.presentacion.ui.views.albumCreate.AlbumCreate
import com.miso.appvinilos.presentacion.ui.views.albumdetail.AlbumCompleteDetail
import com.miso.appvinilos.presentacion.ui.views.albumlist.AlbumList
import com.miso.appvinilos.presentacion.ui.views.artistdetail.ArtistCompleteDetail
import com.miso.appvinilos.presentacion.ui.views.artistlist.ArtistListScreen
import com.miso.appvinilos.presentacion.ui.views.collectordetail.CollectorCompleteDetail
import com.miso.appvinilos.presentacion.ui.views.collectorlist.CollectorList
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel
import com.miso.appvinilos.presentacion.viewmodels.CollectorViewModel
import androidx.navigation.compose.currentBackStackEntryAsState


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            AppVinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                    color = MaterialTheme.colorScheme.background


                ) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavHostController,
    albumsTest: List<Album> = emptyList(),
    artistsTest: List<Artist> = emptyList(),
    collectorsTest: List<Collector> = emptyList()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route
    Log.d("CURRENT_ROUTE", "Ruta $backStackEntry")
    Log.d("CURRENT_ROUTE", "Ruta $route")
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (route == "Albums") {
                FloatingActionButton(onClick = { navController.navigate("createAlbum") }, modifier = Modifier.semantics {
                    stateDescription = "Este boton agrega un nuevo elemento en la lista de datos"
                }) {
                    Icon(Icons.Filled.Add, "Add")
                }
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
            Navigations(
                navController = navController,
                albumsTest = albumsTest,
                artistsTest = artistsTest,
                collectorsTest = collectorsTest
            )
        }
    }
}


sealed class NavigationItem(val route: String, val title: String, val contentDescription: String?, val icon: Int) {

    data object Albums : NavigationItem("Albums", "Albums", "Navigate to Albums", R.drawable.ic_album)  // No constructor needed
    data object Artist : NavigationItem("Artist", "Artist", "Navigate to Artists", R.drawable.ic_artist)
    data object Collector : NavigationItem("Collector", "Collector", "Navigate to Collector", R.drawable.ic_collector)
    data object Home : NavigationItem("Home", "Home", "Navigate to Home", R.drawable.ic_home)
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
                modifier = Modifier.testTag(item.title)
                    .semantics { contentDescription = item.contentDescription.toString() },
                icon = {
                    val imagePainter = painterResource(id = item.icon)
                    Image(
                        painter = imagePainter,
                        contentDescription = "Icono para la opción de menu " + item.title
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
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigations(
    navController: NavHostController,
    albumsTest: List<Album> = emptyList(),
    artistsTest: List<Artist> = emptyList(),
    collectorsTest: List<Collector> = emptyList()
) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Albums.route) {
            AlbumListScreen(navController, albumsTest = albumsTest)
        }
        composable("AlbumCompleteDetail/{albumId}") { backStackEntry ->

            val albumId = backStackEntry.arguments?.getString("albumId")
            val albumIdInt = albumId?.toInt() ?: 0

            AlbumCompleteDetail(albumIdInt, navController, albumsTest = albumsTest)
        }
        composable(NavigationItem.Artist.route) {
            ArtistListScreen(navController, artistTest = artistsTest)
        }
        composable("ArtistCompleteDetail/{artistId}") { backStackEntry ->

            val artistId = backStackEntry.arguments?.getString("artistId")
            val artistIdInt = artistId?.toInt() ?: 0

            ArtistCompleteDetail(artistIdInt, navController, artistTest = artistsTest)
        }
        composable(NavigationItem.Collector.route) {
            CollectorListScreen(navController, collectorsTest = collectorsTest)
        }

        composable("CollectorCompleteDetail/{collectorId}") { backStackEntry ->

            val collectorId = backStackEntry.arguments?.getString("collectorId")
            val collectorIdInt = collectorId?.toInt() ?: 0
            CollectorCompleteDetail(collectorIdInt, navController,collectorsTest = collectorsTest,collectorAlbumsTest = albumsTest)
        }
        
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable("CreateAlbum") {
            CreateAlbumScreen(navController)
        }
        composable("AddComment/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId")?.toInt() ?: 0
            AddCommentScreen(albumId = albumId, navigationController = navController)
        }
    }
}

@Composable
fun CenterText(text: String) {
    Column(
        modifier = Modifier.fillMaxSize().semantics(mergeDescendants = true){},
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics {
                heading()
            }.semantics {
                contentDescription = "Texto de bienvenida"
            },
            fontSize = 32.sp
        )
    }
}


@Composable
fun HomeScreen() {
    CenterText(text = "Bienvenido")
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateAlbumScreen(navController: NavHostController) {
    val viewModel: AlbumViewModel = viewModel()
    AlbumCreate(viewModel, navController)
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AlbumListScreen(
    navigationController: NavHostController,
    albumsTest: List<Album> = emptyList()
) {

    val viewModel: AlbumViewModel = viewModel()

    if (albumsTest.isNotEmpty()) {
        LaunchedEffect(key1 = true) {
            viewModel.fetchAlbums(albumsTest)
        }
    } else {
        LaunchedEffect(key1 = true) {
            viewModel.fetchAlbums()
        }
    }


    Log.d("AlbumListScreen", "Loading albums")
    AlbumList(viewModel, navigationController)
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun CollectorListScreen(
    navigationController: NavHostController,
    collectorsTest: List<Collector> = emptyList()
) {

    val viewModel: CollectorViewModel = viewModel()

    if (collectorsTest.isNotEmpty()) {
        LaunchedEffect(key1 = true) {
            viewModel.fetchCollectors(collectorsTest)
        }
    } else {
        LaunchedEffect(key1 = true) {
            viewModel.fetchCollectors()
        }
    }



    CollectorList(viewModel, navigationController)
}
