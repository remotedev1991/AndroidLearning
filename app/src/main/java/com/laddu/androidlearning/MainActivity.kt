package com.laddu.androidlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.laddu.androidlearning.ui.theme.AndroidLearningTheme

import com.laddu.androidlearning.viewmodel.SharedViewModel
import com.laddu.androidlearning.ui.theme.AndroidLearningTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedViewModel: SharedViewModel = viewModel()
            AndroidLearningTheme(darkTheme = sharedViewModel.isDarkMode) {
                BottomNavigationExample(sharedViewModel = sharedViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidLearningTheme {
        Greeting("Android")
    }
}