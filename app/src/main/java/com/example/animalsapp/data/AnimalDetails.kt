package com.example.animalsapp.data

data class AnimalDetails(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val source: String,
    val status: StatusX,
    val text: String,
    val type: String,
    val updatedAt: String,
    val used: Boolean,
    val user: User
)