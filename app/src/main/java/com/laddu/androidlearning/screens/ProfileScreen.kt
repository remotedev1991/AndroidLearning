package com.laddu.androidlearning.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    var isEditing by rememberSaveable { mutableStateOf(value = false) }
    var name by rememberSaveable { mutableStateOf(value = "John Doe") }
    var email by rememberSaveable { mutableStateOf(value = "john.doe@example.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(80.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        if (isEditing) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        } else {
            Text(name, style = MaterialTheme.typography.headlineSmall)
            Text(email, style = MaterialTheme.typography.bodyMedium)
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { isEditing = !isEditing },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isEditing) "Save Profile" else "Edit Profile")
        }
    }
}
