package com.conaobiad.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(scope: CoroutineScope, drawerState: DrawerState) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Co na obiad?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Bold,
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open() // Otwórz menu boczne
                    }
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Otwórz menu boczne")
                }
            }
        )

        // Spacer dodający odstęp, bez zmiany pozycji ObiadBox
        Spacer(modifier = Modifier.height(1.dp)) // Możesz zmienić wartość 20.dp na odpowiednią

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(), // Usuwa padding, który wpłynąłby na całą zawartość
            thickness = 3.dp,
            color = androidx.compose.ui.graphics.Color.Black
        )
    }
}
