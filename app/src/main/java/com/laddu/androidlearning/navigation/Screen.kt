package com.laddu.androidlearning.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HMobiledata
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String, val label: String, val icon: ImageVector)  {
    object Home: Screen("Home", "Home", Icons.Default.Home)
    object Search: Screen("Search", "Search", Icons.Default.Search)
    object Cart: Screen("Cart", "Cart", Icons.Default.ShoppingCart)
    object Profile: Screen("Profile", "Profile", Icons.Default.Person)
    object Settings: Screen("Settings", "Settings", Icons.Default.Settings)
}


val navItems = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Cart,
    Screen.Profile,
    Screen.Settings
)