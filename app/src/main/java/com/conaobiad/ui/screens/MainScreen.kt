package com.conaobiad.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.conaobiad.data.ApiHandler
import com.conaobiad.data.model.Obiad
import com.conaobiad.ui.components.ObiadBox



@Composable
fun MainScreen(navController: NavController, obiady: List<Obiad>) {
    var isRefreshing by remember { mutableStateOf(false) }
    var obiady by remember { mutableStateOf<List<Obiad>>(emptyList()) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Użycie SwipeRefreshLayout
        val swipeRefreshLayout = remember { SwipeRefreshLayout(context) }

        // Konfiguracja SwipeRefreshLayout
        swipeRefreshLayout.apply {
            setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light)
            setOnRefreshListener {
                isRefreshing = true
                ApiHandler(context).fetchObiady(
                    onSuccess = { fetchedObiady ->
                        isRefreshing = false
                        obiady = fetchedObiady
                    },
                    onError = { error ->
                        isRefreshing = false
                        Toast.makeText(context, "Błąd: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // Wrapping swipeRefreshLayout w Compose
        swipeRefreshLayout.isRefreshing = isRefreshing

        // Zawartość listy w SwipeRefreshLayout
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp), // Dajemy odstęp od góry
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(obiady) { obiad ->
                ObiadBox(obiad = obiad) // Wyświetlanie każdego obiadu
            }
        }
    }

    // Początkowe pobranie danych
    LaunchedEffect(Unit) {
        ApiHandler(context).fetchObiady(
            onSuccess = { fetchedObiady ->
                obiady = fetchedObiady // Zaktualizuj dane obiadowe
            },
            onError = {
                Toast.makeText(context, "Brak polaczenia z internetem", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
