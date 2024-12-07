package com.conaobiad.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import com.conaobiad.R
import kotlinx.coroutines.launch



@Composable
fun SidebarContent(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(260.dp)
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        SidebarHeader() // Header

        Spacer(modifier = Modifier.height(16.dp))

        SidebarButton(
            text = "Co na obiad?",
            icon = Icons.Filled.Home,
            onClick = {
                navController.navigate("home_screen") // Navigate to Home screen
                scope.launch {
                    drawerState.close() // Close the drawer
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SidebarButton(
            text = "Github",
            icon = ImageVector.vectorResource(id = R.drawable.github_vector),
            onClick = {
                // TODO: Handle GitHub action
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SidebarButton(
            text = "O Aplikacji",
            icon = Icons.Filled.Info,
            onClick = {
                navController.navigate("about_screen") // Navigate to About screen
                scope.launch {
                    drawerState.close() // Close the drawer
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        SidebarButton(
            text = "Ustawienia",
            icon = Icons.Filled.Settings,
            onClick = {

                scope.launch{
                    drawerState.close()
                }

                navController.navigate("settings_screen") // Navigate to Settings screen

            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SidebarButton(
            text = "Zgłoś błąd/pomysł",
            icon = Icons.Filled.Warning,
            onClick = {
                // TODO: Handle error/idea reporting
            },
            isSpecialButton = true
        )
    }
}
