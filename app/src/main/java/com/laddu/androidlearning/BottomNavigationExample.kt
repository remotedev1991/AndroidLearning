package com.laddu.androidlearning

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.laddu.androidlearning.navigation.Screen
import com.laddu.androidlearning.navigation.navItems
import com.laddu.androidlearning.screens.CartScreen
import com.laddu.androidlearning.screens.HomeScreen
import com.laddu.androidlearning.screens.ProfileScreen
import com.laddu.androidlearning.screens.SearchScreen
import com.laddu.androidlearning.screens.SettingsScreen
import com.laddu.androidlearning.ui.theme.AndroidLearningTheme
import com.laddu.androidlearning.viewmodel.SharedViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationExample(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel = viewModel()
) {

    val navController = rememberNavController()

    val snackBarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentTitle = when (currentDestination?.route) {
        Screen.Home.route -> "Welcome Home"
        Screen.Search.route -> "Find Products"
        Screen.Cart.route -> "Your Shopping Cart"
        Screen.Profile.route -> "My Account"
        Screen.Settings.route -> "App Settings"
        else -> "Android Learning"
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(currentTitle) //Dynamic title Update
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                navItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(screen.label)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if ((screen is Screen.Cart) && sharedViewModel.cartCount > 0) {
                                        Badge {
                                            Text(sharedViewModel.cartCount.toString())
                                        }
                                    }
                                }
                            ) {
                                Icon(imageVector = screen.icon, contentDescription = screen.label)
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(sharedViewModel, onShowSnackbar = { message ->
                    scope.launch {
                        snackBarHostState.currentSnackbarData?.dismiss() //previous snackbars
                        snackBarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Short
                        )
                    }
                })
            }
            composable(route = Screen.Search.route) {
                SearchScreen()
            }
            composable(route = Screen.Cart.route) {
                CartScreen(sharedViewModel = sharedViewModel) { message ->
                    scope.launch {
                        snackBarHostState.currentSnackbarData?.dismiss()
                        snackBarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen()
            }
            composable(route = Screen.Settings.route) {
                SettingsScreen(sharedViewModel = sharedViewModel)
            }
        }
    }
}


@Preview
@Composable
fun BottomNavigationExamplePreview() {
    AndroidLearningTheme {
        BottomNavigationExample()
    }
}