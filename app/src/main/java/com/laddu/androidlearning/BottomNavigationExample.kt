package com.laddu.androidlearning

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.laddu.androidlearning.navigation.Screen
import com.laddu.androidlearning.navigation.navItems
import com.laddu.androidlearning.screens.*
import com.laddu.androidlearning.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationExample(sharedViewModel: SharedViewModel = viewModel()) {
    val navController = rememberNavController()
    
    // 1. Snackbar state & Coroutine Scope
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    // 2. Haptic Feedback (The "Pro" vibration)
    val haptic = LocalHapticFeedback.current

    // 3. Observe the current backstack entry for the dynamic title
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    // Determine the title based on the current route
    val currentTitle = when (currentDestination?.route) {
        Screen.Home.route -> "Welcome Home"
        Screen.Search.route -> "Find Products"
        Screen.Cart.route -> "Your Shopping Cart"
        Screen.Profile.route -> "My Account"
        Screen.Settings.route -> "App Settings"
        else -> "Android Learning"
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(currentTitle) }, // Dynamic Title!
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            BadgedBox(
                                badge = {
                                    if ((screen is Screen.Cart) && (sharedViewModel.cartCount > 0)) {
                                        Badge {
                                            Text(sharedViewModel.cartCount.toString())
                                        }
                                    }
                                }
                            ) {
                                Icon(screen.icon, contentDescription = screen.label)
                            }
                        },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            // Trigger haptic feedback
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
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
            composable(Screen.Home.route) { 
                HomeScreen(sharedViewModel) { message ->
                    scope.launch {
                        // Dismiss current snackbar to show the new one immediately
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Short
                        )
                    }
                } 
            }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Cart.route) { 
                CartScreen(sharedViewModel) { message ->
                    scope.launch {
                        // Dismiss current snackbar to show the new one immediately
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Short
                        )
                    }
                } 
            }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Settings.route) { SettingsScreen(sharedViewModel) }
        }
    }
}
