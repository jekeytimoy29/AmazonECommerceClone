package com.example.amazonecommerceclone.model

import java.io.Serializable

data class User(
    var name: String?,
    var emailOrNumber: String,
    var password: String,
    var cartItemsList: MutableList<Product>?
) : Serializable
