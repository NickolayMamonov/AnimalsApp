package com.example.animalsapp

import com.example.animalsapp.models.animal.Animal

sealed class MainScreenState{
    data class Content(val animal: List<Animal>) :MainScreenState()
    data class Error(val th: Throwable) : MainScreenState()
    object Loading : MainScreenState()
}
