package com.example.cis436_project3cats

//created class to make it easier to transfer cat informatin between fragments and main activity
data class CatInfo(
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val imageURL: String
)
