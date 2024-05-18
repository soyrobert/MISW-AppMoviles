package com.miso.appvinilos.presentacion.ui.views.albumCreate

import android.os.Build
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@RequiresApi(Build.VERSION_CODES.O)
fun validateAndConvertDate(dateString: String): String? {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00XXX")
    return try {
        val localDate = LocalDate.parse(dateString, inputFormatter)
        val zonedDateTime = localDate.atStartOfDay(ZoneId.of("UTC-05:00"))
        zonedDateTime.format(outputFormatter)
    } catch (e: DateTimeParseException) {
        null
    }
}

fun validateUrl(url: String): Boolean {
    return Patterns.WEB_URL.matcher(url).matches()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlbumCreate(viewModel: AlbumViewModel, navigationController: NavHostController) {
    var name by rememberSaveable { mutableStateOf("") }
    var cover by rememberSaveable { mutableStateOf("") }
    var genre by rememberSaveable { mutableStateOf("") }
    var recordLabel by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val albumCreationResponse by viewModel.postAlbumResponse.observeAsState()

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Crear ALbum",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(8.dp))

        var isNameValid by remember { mutableStateOf(true) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
                isNameValid = name.isNotEmpty()
            },
            label = { Text("Nombre") }
        )
        if (!isNameValid) {
            Text(text = "El nombre no debe estar vacio", color = Color.Red)
        }

        Spacer(modifier = Modifier.padding(2.dp))

        var isCoverValid by remember { mutableStateOf(true) }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = cover,
            onValueChange = {
                cover = it
                isCoverValid = validateUrl(cover)
                if (!isCoverValid) {
                    Toast.makeText(context, "URL invalido", Toast.LENGTH_LONG).show()
                }
            },
            label = { Text("URL") },
            isError = !isCoverValid
        )
        if (!isCoverValid) {
            Text("Ingrese un formato URL valido.", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.padding(2.dp))

        var isGenreValid by remember { mutableStateOf(true) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = genre,
            onValueChange = { genre = it
                isGenreValid = genre.isNotEmpty() },
            placeholder = { Text(text = "Género") },
        )
        if (!isGenreValid) {
            Text(text = "El genero no debe estar vacio", color = Color.Red)
        }
        Spacer(modifier = Modifier.padding(2.dp))

        var isDateValid by remember { mutableStateOf(true) }
        TextField(
            value = releaseDate,
            onValueChange = {
                releaseDate = it
                val formattedDate = validateAndConvertDate(releaseDate)
                if (formattedDate != null) {
                    releaseDate = formattedDate
                    isDateValid = true
                } else {
                    isDateValid = false
                }
            },
            label = { Text("Release Date") },
            isError = !isDateValid
        )

        if (!isDateValid) {
            Text("Agregar un formato valido de fecha yyyy-MM-dd", color = MaterialTheme.colorScheme.error)
        }

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

        Button(onClick = {
            val album = Album(
                name = name, cover = cover,
                releaseDate = releaseDate, description = description,
                genre = genre, recordLabel = recordLabel
            )
            Log.d("Album", "Album: $album")
            viewModel.createAlbum(album)
        }) { Text("Create Album") }

        albumCreationResponse?.let { response ->
            if (response.isSuccessful) {
                Text("Se creo el album correctamente!")
                LaunchedEffect(Unit) {
                    navigationController.popBackStack()
                }
            } else {
                Text("Error al crear el album: ${response.message()}", color = Color.Red)
            }
        }
    }
}