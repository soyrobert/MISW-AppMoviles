package com.miso.appvinilos.accesibiliad

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.miso.appvinilos.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeAccesibilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            HomeScreen()
        }
    }

    @Test
    fun test_pantalla_inicio_contiene_texto_de_bienvenida_y_es_accesible() {
        val contentDescription = "Texto de bienvenida"

        composeTestRule.onNodeWithContentDescription(contentDescription)
            .assertExists()
            .assert(hasContentDescription(contentDescription))

    }

    @Test
    fun test_pantalla_inicio_contiene_texto_de_bienvenida() {
        val welcomeText = "Bienvenido"
        composeTestRule.onNodeWithText(welcomeText).assertIsDisplayed()
    }

}