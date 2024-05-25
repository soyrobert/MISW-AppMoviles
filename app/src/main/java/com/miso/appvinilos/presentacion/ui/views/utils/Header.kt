package com.miso.appvinilos.presentacion.ui.views.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Header(text:String,navigationController: NavHostController) {
    Surface(

        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopBar(text, navigationController)
        }
    }
}

@Composable
fun TopBar(text: String, navigationController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { navigationController.popBackStack()},modifier= Modifier.testTag("backButton")) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
        }
        Title(text)
    }
}

@Preview
@Composable
fun Title(text: String="Artista") {
    Text(
        text = text,
        style = TextStyle(
            color = Color(0xFF1B1C17),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
        ),
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}