package com.miso.appvinilos.presentacion.ui.views.albumCreate
import androidx.navigation.NavHostController
import android.os.Build
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.appvinilos.data.model.Album
import com.miso.appvinilos.presentacion.viewmodels.AlbumViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@RequiresApi(Build.VERSION_CODES.O)
fun validateAndConvertDate(dateString: String): String? {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00XX")
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
            text = "Crear Album",
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

        var isGenreError by remember { mutableStateOf(false) }
        var genreErrorMessage by remember { mutableStateOf("") }
        val validGenreLabels = listOf("Classical", "Salsa", "Rock", "Flok")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = genre,
            onValueChange = {
                genre = it
                if (genre in validGenreLabels) {
                    isGenreError = false
                    genreErrorMessage = ""
                } else {
                    isGenreError = true
                    genreErrorMessage = "Opciones validas: Classical, Salsa, Rock, Flok"
                }
            },
            placeholder = { Text(text = "Genero") },
            isError = isGenreError
        )

        if (isGenreError) {
            Text(genreErrorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.padding(2.dp))

        ReleaseDateTextField { releaseDate = it }
        if (releaseDate == null) {
            releaseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00XX"))
        }

        Spacer(modifier = Modifier.padding(2.dp))

        var isRecordError by remember { mutableStateOf(false) }
        var recordErrorMessage by remember { mutableStateOf("") }
        val validLabels = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = recordLabel,
            onValueChange = {
                recordLabel = it
                if (recordLabel in validLabels) {
                    isRecordError = false
                    recordErrorMessage = ""
                } else {
                    isRecordError = true
                    recordErrorMessage = "Opciones validas: Sony Music, EMI, Discos Fuentes, Elektra, Fania Records"
                }
            },
            placeholder = { Text(text = "Compañía Discografica") },
            isError = isRecordError
        )

        if (isRecordError) {
            Text(recordErrorMessage, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.padding(2.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            placeholder = { Text(text = "Descripción") },
        )
        Spacer(modifier = Modifier.padding(2.dp))

        Button(onClick = {
            if (isNameValid && isCoverValid && releaseDate.isNotBlank()
                && description.isNotBlank() && !isGenreError && !isRecordError){
                val album = Album(
                    name = name, cover = cover,
                    releaseDate = releaseDate, description = description,
                    genre = genre, recordLabel = recordLabel
                )
                Log.d("Album", "Album: $album")
                viewModel.createAlbum(album) }
                         },
            enabled = (isNameValid && isCoverValid && releaseDate.isNotBlank()
                    && description.isNotBlank() && !isGenreError && !isRecordError),
            colors = ButtonDefaults.buttonColors(
                if (isNameValid && isCoverValid && releaseDate.isNotBlank() && description.isNotBlank() && !isGenreError && !isRecordError) MaterialTheme.colorScheme.primary else Color.Gray
            ),
            modifier = Modifier
                .testTag("SubmitAlbumButton")
            ) { Text("Create Album") }

        albumCreationResponse?.let { response ->
            if (response.isSuccessful) {
                Text("Se creo el album correctamente!")
                LaunchedEffect(Unit) {
                    navigationController.navigate("Albums")
                }
            } else {
                Text("Error al crear el album: ${response.message()}", color = Color.Red)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReleaseDateTextField(releaseDate: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
    var selectedDate by rememberSaveable { mutableStateOf(currentDate) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    Date().also { calendar.time = it }

    val datePickerDialog =
        DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val localDate = LocalDate.of(year, month + 1, dayOfMonth)
            selectedDate = localDate.format(DateTimeFormatter.ISO_DATE)
            val formattedDate = validateAndConvertDate(selectedDate)
            if (formattedDate != null) {
                releaseDate(formattedDate)
            }
        }, year, month, day)

    var isDateValid by remember { mutableStateOf(true) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "Release Date TextField" },
        readOnly = true,
        value = selectedDate,
        onValueChange = {
            selectedDate = it // Update selected date
            val formattedDate = validateAndConvertDate(it)
            Log.d("Formatted", "Formatted Date: $formattedDate")
            if (formattedDate != null) {
                releaseDate(formattedDate)
                isDateValid = true
            } else {
                isDateValid = false
            }
        },
        trailingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Date Range Icon",
                modifier = Modifier.semantics { contentDescription = "Date Range Icon" }
            )
        },
        interactionSource = interactionSource
    )

    if (!isDateValid) {
        Text("Please enter a valid date", color = MaterialTheme.colorScheme.error)
    }

    if (isPressed) {
        datePickerDialog.show()
    }
}