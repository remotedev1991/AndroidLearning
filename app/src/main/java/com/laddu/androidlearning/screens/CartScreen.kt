package com.laddu.androidlearning.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.laddu.androidlearning.viewmodel.SharedViewModel

@Composable
fun CartScreen(viewModel: SharedViewModel, onShowSnackbar: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.cartCount == 0) {
            Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(100.dp), tint = Color.LightGray)
            Text("Your cart is empty", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Go to Home to add items!", color = Color.Gray)
        } else {
            Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(100.dp), tint = Color.Blue)
            Text("Items to checkout: ${viewModel.cartCount}", style = MaterialTheme.typography.headlineSmall)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(onClick = { 
                viewModel.addToCart() 
                onShowSnackbar("Another item added!")
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Add one more")
            }
            
            Button(
                onClick = { 
                    viewModel.clearCart() 
                    onShowSnackbar("Cart cleared successfully")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.size(8.dp))
                Text("Clear Cart")
            }
        }
    }
}
