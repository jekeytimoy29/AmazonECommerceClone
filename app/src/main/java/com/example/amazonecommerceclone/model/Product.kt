package com.example.amazonecommerceclone.model

import java.io.Serializable

data class Product(
    var name:String,
    var description:String,
    var imgSrc:Int,
    var logoSrc:Int,
    var price:Double
) : Serializable
