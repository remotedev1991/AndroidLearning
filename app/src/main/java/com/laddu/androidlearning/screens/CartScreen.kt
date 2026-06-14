package com.laddu.androidlearning.screens

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.laddu.androidlearning.viewmodel.SharedViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    onShowSnackBar: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (sharedViewModel.cartCount == 0) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.Gray
            )

            Text("Your Cart is empty", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Go to Home to add items!", color = Color.Gray)

        } else {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.Blue
            )

            Text(
                "Items to checkout ${sharedViewModel.cartCount}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                sharedViewModel.addToCart()
                onShowSnackBar("Another item added!")
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Add one more")
            }

            Button(
                onClick = {
                    sharedViewModel.clearCart()
                    onShowSnackBar("Cart cleared successfully")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.size(8.dp))
                Text("Clear Cart")
            }

        }
    }

}