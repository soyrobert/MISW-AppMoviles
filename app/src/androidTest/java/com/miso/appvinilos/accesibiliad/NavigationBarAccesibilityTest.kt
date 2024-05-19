package com.miso.appvinilos.accesibiliad

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.miso.appvinilos.BottomNavigationBar
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationBarAccesibilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            val navigationController = rememberNavController()
            BottomNavigationBar(navController = navigationController)
        }
    }

    @Test
    fun test_menu_albums_contiene_content_description() {
        val content_description = "Navigate to Albums"
        composeTestRule.onNodeWithContentDescription(content_description)
            .assertExists()
            .assert(hasContentDescription(content_description))
    }

    @Test
    fun test_menu_albums_contiene_click_action() {
        composeTestRule.onNodeWithText("Albums")
            .assertExists()
            .assert(hasClickAction())
    }

    @Test
    fun test_menu_artist_contiene_content_description() {
        val content_description = "Navigate to Artists"
        composeTestRule.onNodeWithContentDescription(content_description)
            .assertExists()
            .assert(hasContentDescription(content_description))
    }

    @Test
    fun test_menu_artist_contiene_click_action() {
        composeTestRule.onNodeWithText("Artist")
            .assertExists()
            .assert(hasClickAction())
    }

    @Test
    fun test_menu_collector_contiene_content_description() {
        val content_description = "Navigate to Collector"
        composeTestRule.onNodeWithContentDescription(content_description)
            .assertExists()
            .assert(hasContentDescription(content_description))
    }

    @Test
    fun test_menu_collector_contiene_click_action() {
        composeTestRule.onNodeWithText("Collector")
            .assertExists()
            .assert(hasClickAction())
    }

    @Test
    fun test_menu_volver_a_home_contiene_content_description() {
        val content_description = "Navigate to Home"
        composeTestRule.onNodeWithContentDescription(content_description)
            .assertExists()
            .assert(hasContentDescription(content_description))
    }


    @Test
    fun test_menu_voler_home_contiene_click_action() {
        composeTestRule.onNodeWithText("Home")
            .assertExists()
            .assert(hasClickAction())
    }


}