package com.example.amazonecommerceclone.model

import java.io.Serializable

data class ProductCategory(
    var name: String,
    var imgSrc: Int,
    var type: CategoryType,
    var productsList: MutableList<Product>
) :
    Serializable
