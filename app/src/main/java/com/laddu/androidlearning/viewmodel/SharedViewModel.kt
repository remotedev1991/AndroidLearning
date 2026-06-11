package com.laddu.androidlearning.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var cartCount by mutableIntStateOf(0)
        private set

    var isDarkMode by mutableStateOf(value = false)
        private set

    fun addToCart() {
        cartCount++
    }

    fun clearCart() {
        cartCount = 0
    }

    fun toggleDarkMode(enabled: Boolean) {
        isDarkMode = enabled
    }
}
