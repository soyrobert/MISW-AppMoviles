package com.miso.appvinilos.e2e

import org.junit.Test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.AlbumListScreen
import com.miso.appvinilos.albums.model.Album
import com.miso.appvinilos.albums.ui.theme.AppVinilosTheme
import com.miso.appvinilos.albums.ui.views.AlbumCompleteDetail
import org.junit.Before
import org.junit.Rule

class E2ETests {
    val albumTest1=Album(id = 1, name="Album1", cover="cover1", releaseDate="2021-01-01", description="description1", genre="genre1", recordLabel="recordLabel1")
    val albumTest2=Album(id = 2, name="Album2", cover="cover2", releaseDate="2021-01-02", description="description2", genre="genre2", recordLabel="recordLabel2")
    val albumTest3=Album(id = 3, name="Album3", cover="cover3", releaseDate="2021-01-03", description="description3", genre="genre3", recordLabel="recordLabel3")
    val albumTest4=Album(id = 4, name="Album4", cover="cover4", releaseDate="2021-01-04", description="description4", genre="genre4", recordLabel="recordLabel4")
    val albumTest5=Album(id = 5, name="Album5", cover="cover5", releaseDate="2021-01-05", description="description5", genre="genre5", recordLabel="recordLabel5")
    val albumTest6=Album(id = 6, name="Album6", cover="cover6", releaseDate="2021-01-06", description="description6", genre="genre6", recordLabel="recordLabel6")
    val albumTest7=Album(id = 7, name="Album7", cover="cover7", releaseDate="2021-01-07", description="description7", genre="genre7", recordLabel="recordLabel7")
    val albumTest8=Album(id = 8, name="Album8", cover="cover8", releaseDate="2021-01-08", description="description8", genre="genre8", recordLabel="recordLabel8")
    val albumsTest = listOf(albumTest1, albumTest2, albumTest3, albumTest4, albumTest5, albumTest6, albumTest7, albumTest8)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){

        composeTestRule.setContent {
            //TODO se debe hacer refactor dado que es el mismo codigo en MainActivity
            AppVinilosTheme {
                val navigationController= rememberNavController()
                NavHost(navController = navigationController, startDestination = "AlbumListScreen"){
                    composable("AlbumListScreen"){ AlbumListScreen(navigationController,albumsTest) }
                    composable("AlbumCompleteDetail/{albumId}"){ backStackEntry ->
                        var albumId=backStackEntry.arguments?.getString("albumId")
                        //var albumEjemplo = Album(1, "Album de ejemplo", "cover", "releaseDate","descr","genre","recordlab")
                        var albumIdInt= albumId?.toInt()?:0
                        AlbumCompleteDetail(albumIdInt,navigationController,albumsTest)
                    }
                }

            }


        }
    }

    @Test
    fun test_caso_uso_1(){
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).performClick()
        composeTestRule.onNodeWithText("Álbum").assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(albumTest2.name).assertIsDisplayed()
    }
    @Test
    fun test_caso_uso_2(){
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(7)
        composeTestRule.onNodeWithText(albumTest8.name).performClick()
        composeTestRule.onNodeWithText("Álbum").assertIsDisplayed()
        composeTestRule.onNodeWithText(albumTest8.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText(albumTest1.name).assertIsDisplayed()
    }


}