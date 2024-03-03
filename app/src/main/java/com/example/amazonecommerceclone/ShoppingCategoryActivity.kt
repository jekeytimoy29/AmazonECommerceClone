package com.example.amazonecommerceclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amazonecommerceclone.databinding.ActivityShoppingCategoryBinding
import com.example.amazonecommerceclone.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonString = intent.getStringExtra("user")
        val type = object : TypeToken<User>() {}.type
        user = Gson().fromJson(jsonString, type)

        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeText.text = getString(R.string.welcome_text, user.name)
    }
}