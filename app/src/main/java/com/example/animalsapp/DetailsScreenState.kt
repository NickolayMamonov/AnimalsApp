package com.example.animalsapp

import com.example.animalsapp.models.animaldetails.AnimalDetails

sealed class DetailsScreenState{
    data class Content(val id: AnimalDetails) : DetailsScreenState()
    data class Error(val th: Throwable) : DetailsScreenState()
    object Loading : DetailsScreenState()
}
