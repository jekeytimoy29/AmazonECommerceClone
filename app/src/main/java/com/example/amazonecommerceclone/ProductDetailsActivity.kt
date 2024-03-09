package com.example.amazonecommerceclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.amazonecommerceclone.databinding.ActivityProductDetailsBinding
import com.example.amazonecommerceclone.model.Product
import com.example.amazonecommerceclone.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.NumberFormat

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonString = intent.getStringExtra("product")
        val type = object : TypeToken<Product>() {}.type
        product = Gson().fromJson(jsonString, type)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)

        binding.detailsImage.setImageResource(product.imgSrc)
        binding.detailsName.text = product.name
        binding.detailsDescription.text = product.description
        binding.detailsPrice.text =
            NumberFormat.getCurrencyInstance().format(product.price).toString()

        setContentView(binding.root)
    }

    fun homeButtonOnClick(view: View) {
        finish()
    }
}