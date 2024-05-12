package com.miso.appvinilos.e2e

import org.junit.Test

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.MainScreen
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.ui.theme.AppVinilosTheme
import com.miso.appvinilos.data.model.Artist
import com.miso.appvinilos.data.model.Collector
import com.miso.appvinilos.data.model.Comment
import org.junit.Before
import org.junit.Rule
import androidx.test.espresso.Espresso

class E2ETests {
    private val albumTest1 = Album(
        id = 1,
        name = "Album1",
        cover = "cover1",
        releaseDate = "2021-01-01",
        description = "description1",
        genre = "genre1",
        recordLabel = "recordLabel1"
    )
    private val albumTest2 = Album(
        id = 2,
        name = "Album2",
        cover = "cover2",
        releaseDate = "2021-01-02",
        description = "description2",
        genre = "genre2",
        recordLabel = "recordLabel2"
    )
    private val albumTest3 = Album(
        id = 3,
        name = "Album3",
        cover = "cover3",
        releaseDate = "2021-01-03",
        description = "description3",
        genre = "genre3",
        recordLabel = "recordLabel3"
    )
    private val albumTest4 = Album(
        id = 4,
        name = "Album4",
        cover = "cover4",
        releaseDate = "2021-01-04",
        description = "description4",
        genre = "genre4",
        recordLabel = "recordLabel4"
    )
    private val albumTest5 = Album(
        id = 5,
        name = "Album5",
        cover = "cover5",
        releaseDate = "2021-01-05",
        description = "description5",
        genre = "genre5",
        recordLabel = "recordLabel5"
    )
    private val albumTest6 = Album(
        id = 6,
        name = "Album6",
        cover = "cover6",
        releaseDate = "2021-01-06",
        description = "description6",
        genre = "genre6",
        recordLabel = "recordLabel6"
    )
    private val albumTest7 = Album(
        id = 7,
        name = "Album7",
        cover = "cover7",
        releaseDate = "2021-01-07",
        description = "description7",
        genre = "genre7",
        recordLabel = "recordLabel7"
    )
    private val albumTest8 = Album(
        id = 8,
        name = "Album8",
        cover = "cover8",
        releaseDate = "2021-01-08",
        description = "description8",
        genre = "genre8",
        recordLabel = "recordLabel8"
    )
    private val albumsTest = listOf(
        albumTest1,
        albumTest2,
        albumTest3,
        albumTest4,
        albumTest5,
        albumTest6,
        albumTest7,
        albumTest8
    )

    private val artistTest1 = Artist(
        id = 1,
        name = "Artist1",
        image = "image1",
        description = "description1",
        birthDate = "birthDate1"
    )
    private val artistTest2 = Artist(
        id = 2,
        name = "Artist2",
        image = "image2",
        description = "description2",
        birthDate = "birthDate2"
    )
    private val artistTest3 = Artist(
        id = 3,
        name = "Artist3",
        image = "image3",
        description = "description3",
        birthDate = "birthDate3"
    )
    private val artistTest4 = Artist(
        id = 4,
        name = "Artist4",
        image = "image4",
        description = "description4",
        birthDate = "birthDate4"
    )
    private val artistTest5 = Artist(
        id = 5,
        name = "Artist5",
        image = "image5",
        description = "description5",
        birthDate = "birthDate5"
    )
    private val artistTest6 = Artist(
        id = 6,
        name = "Artist6",
        image = "image6",
        description = "description6",
        birthDate = "birthDate6"
    )
    private val artistTest7 = Artist(
        id = 7,
        name = "Artist7",
        image = "image7",
        description = "description7",
        birthDate = "birthDate7"
    )
    private val artistTest8 = Artist(
        id = 8,
        name = "Artist8",
        image = "image8",
        description = "description8",
        birthDate = "birthDate8"
    )
    private val artistsTest = listOf(
        artistTest1,
        artistTest2,
        artistTest3,
        artistTest4,
        artistTest5,
        artistTest6,
        artistTest7,
        artistTest8
    )

    private val commentsTest = listOf(
        Comment(id = 1, content = "Great collection!"),
        Comment(id = 2, content = "Impressive variety of albums.")
    )

    private val collectorsTest = listOf(
        Collector(
            id = 1,
            name = "Noemi Murillo",
            telephone = "123456789",
            email = "noemi@example.com",
            comments = commentsTest,
            favoritePerformers = artistsTest,
            collectorAlbums = albumsTest
        ),
        Collector(
            id = 2,
            name = "Francisco Bustamante",
            telephone = "987654321",
            email = "francisco@example.com",
            comments = commentsTest,
            favoritePerformers = artistsTest,
            collectorAlbums = albumsTest
        ),
        Collector(
            id = 3,
            name = "Francisco Javier",
            telephone = "987654321",
            email = "francisco@example.com",
            comments = commentsTest,
            favoritePerformers = artistsTest,
            collectorAlbums = albumsTest
        ),
        Collector(
            id = 4,
            name = "Javier Bustamante",
            telephone = "987654321",
            email = "francisco@example.com",
            comments = commentsTest,
            favoritePerformers = artistsTest,
            collectorAlbums = albumsTest
        ),
        Collector(
            id = 5,
            name = "Murillo Francisco Javier",
            telephone = "987654321",
            email = "francisco@example.com",
            comments = commentsTest,
            favoritePerformers = artistsTest,
            collectorAlbums = albumsTest
        ),
        Collector(
            id = 6,
            name = "Francisco Murillo Bustamante",
            telephone = "987654321",
            email = "francisco@example.com",
            comments = commentsTest,
            favoritePerformers = artistsTest,
            collectorAlbums = albumsTest
        )
    )


    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {

        composeTestRule.setContent {
            AppVinilosTheme {
                val navController = rememberNavController()
                AppVinilosTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainScreen(navController = navController,
                            albumsTest = albumsTest,
                            artistsTest = artistsTest,
                            collectorsTest = collectorsTest)
                    }
                }

            }


        }
    }

    @Test
    fun test_1_consulta_album_1() {
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).performClick()
        composeTestRule.onNodeWithText("Álbum").assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(albumTest2.name).assertIsDisplayed()
    }

    @Test
    fun test_2_uso_scroll_album() {
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(7)
        composeTestRule.onNodeWithText(albumTest8.name).assertIsDisplayed()
    }

    @Test
    fun test_3_consulta_artista_1() {
        composeTestRule.onNodeWithText("Artist").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest1.name).performClick()
        composeTestRule.onNodeWithText("Artista").assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(artistTest2.name).assertIsDisplayed()
    }

    @Test
    fun test_4_uso_scroll_artista() {
        composeTestRule.onNodeWithText("Artist").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("ArtistList").performScrollToIndex(7)
        composeTestRule.onNodeWithText(artistTest8.name).assertIsDisplayed()

    }

    @Test
    fun test_5_ver_coleccionistas() {
        composeTestRule.onNodeWithText("Collector").performClick()
        composeTestRule.onNodeWithText(collectorsTest[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(collectorsTest[1].name).assertIsDisplayed()
    }

    @Test
    fun test_6_uso_album_uso_artista() {
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest1.name).performClick()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.onAllNodesWithText("Home").assertCountEquals(2)
    }

    @Test
    fun test_7_uso_completo_app() {
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText("Artist").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest1.name).performClick()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()

        composeTestRule.onNodeWithText("Collector").performClick()
        composeTestRule.onNodeWithText(collectorsTest[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(collectorsTest[1].name).assertIsDisplayed()

        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText("Artist").performClick()
        composeTestRule.onNodeWithText("Collector").performClick()

        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.onAllNodesWithText("Home").assertCountEquals(2)
    }

    @Test
    fun test_8_revision_stack_desde_album(){
        composeTestRule.onAllNodesWithText("Home").assertCountEquals(2)
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()

        //al hacer el back en el dispositivo se debe retornar al home
        Espresso.pressBack()
        composeTestRule.onAllNodesWithText("Home").assertCountEquals(2)

    }

    @Test
    fun test_9_revision_stack_desde_artista(){
        composeTestRule.onAllNodesWithText("Home").assertCountEquals(2)
        composeTestRule.onNodeWithText("Artist").performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(artistTest1.name).performClick()
        composeTestRule.onNodeWithText(artistTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()

        //al hacer el back en el dispositivo se debe retornar al home
        Espresso.pressBack()
        composeTestRule.onAllNodesWithText("Home").assertCountEquals(2)

    }


}