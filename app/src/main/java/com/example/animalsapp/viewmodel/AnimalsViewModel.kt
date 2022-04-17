package com.example.animalsapp.viewmodel

import com.example.animalsapp.repository.AnimalsDBRepository
import com.example.animalsapp.repository.AnimalsDBRepositoryImpl

class AnimalsViewModel {

    private val aAnimalsRepository:AnimalsDBRepository = AnimalsDBRepositoryImpl()

    fun getAnimals(): String {
        return aAnimalsRepository.getAnimals()
    }
}