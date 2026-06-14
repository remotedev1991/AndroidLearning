package com.laddu.androidlearning.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laddu.androidlearning.viewmodel.SharedViewModel

@Composable
fun HomeScreen(sharedViewModel: SharedViewModel, onShowSnackbar: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")
        Text("Current items in cart: ${sharedViewModel.cartCount}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            sharedViewModel.addToCart()
            onShowSnackbar("Item added from Home!")
        }) {
            Text("Add to Cart from Home")
        }
    }
}