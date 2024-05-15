package com.miso.appvinilos.presentacion.ui.views.albumCreate

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.appvinilos.R
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel


@Composable
fun AlbumCreate(viewModel: AlbumViewModel) {
    var name by rememberSaveable { mutableStateOf("") }
    var cover by rememberSaveable { mutableStateOf("") }
    var genre by rememberSaveable { mutableStateOf("") }
    var recordLabel by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var releaseDate  by rememberSaveable { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.createAlbum),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { input ->
                name = input
                isNameValid = name.isNotEmpty()
            },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.padding(2.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = cover,
            onValueChange = { cover = it },
            placeholder = { Text(text = "URL Portada") },
        )
        Spacer(modifier = Modifier.padding(2.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = genre,
            onValueChange = { genre = it },
            placeholder = { Text(text = "Género") },
        )
        Spacer(modifier = Modifier.padding(2.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = releaseDate,
            onValueChange = { releaseDate = it },
            placeholder = { Text(text = "Fecha de Publicacion") },
        )
        Spacer(modifier = Modifier.padding(2.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = recordLabel,
            onValueChange = { recordLabel = it },
            placeholder = { Text(text = "Compañía Discografica") },
        )
        Spacer(modifier = Modifier.padding(2.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            placeholder = { Text(text = "Descripción") },
        )
        Spacer(modifier = Modifier.padding(2.dp))
        val album = Album(
            name =name, cover =cover,
            releaseDate = "1984-08-01T00:00:00-05:00", description =description,
            genre =genre, recordLabel =recordLabel)
        Log.d("Album", "Album: $album")
        Button(onClick = { viewModel.createAlbum(album) }) { Text("Create Album") }
    }
}
