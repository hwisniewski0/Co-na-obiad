package com.conaobiad.ui
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.conaobiad.R
import com.conaobiad.data.model.Obiad
import com.conaobiad.ui.components.SidebarContent
import com.conaobiad.ui.screens.MainScreen
import com.conaobiad.ui.screens.AboutScreen
import com.conaobiad.ui.screens.SettingsScreen


val montserratFont = FontFamily(
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold)
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(context: Context, obiady: List<Obiad>) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SidebarContent(
                drawerState = drawerState,
                scope = scope,
                navController = navController
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBarApp(scope = rememberCoroutineScope(), drawerState = drawerState)
            },
            content = { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = "home_screen",
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable("home_screen") { MainScreen(navController, obiady) }
                    composable("about_screen") { AboutScreen(navController) }
                    composable("settings_screen") { SettingsScreen(navController) }
                }
            }
        )
    }
}
