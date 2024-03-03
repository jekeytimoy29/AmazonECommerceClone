package com.example.amazonecommerceclone.model

import java.io.Serializable

data class User(
    var name: String?,
    val emailOrNumber: String,
    var password: String,
    var categoriesList: MutableList<ShoppingCategory>?
) : Serializable
