package com.example.amazonecommerceclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.amazonecommerceclone.adapter.CategoryAdapter
import com.example.amazonecommerceclone.databinding.ActivityShoppingCategoryBinding
import com.example.amazonecommerceclone.model.ShoppingCategory
import com.example.amazonecommerceclone.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    private lateinit var user: User
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonString = intent.getStringExtra("user")
        val type = object : TypeToken<User>() {}.type
        user = Gson().fromJson(jsonString, type)

        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeText.text = getString(R.string.welcome_text, user.name)

        val categoryList =
            listOf<ShoppingCategory>(
                ShoppingCategory("Home and Kitchen", R.drawable.kitchen),
                ShoppingCategory("Beauty and Personal Care", R.drawable.nail),
                ShoppingCategory("Pet supplies", R.drawable.pet_food),
                ShoppingCategory("Toys and Games", R.drawable.lego)
            )

        categoryAdapter = CategoryAdapter(this, categoryList) { category ->
            Toast.makeText(
                this,
                getString(R.string.category_selected, category.name),
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.shoplistCategory.layoutManager = GridLayoutManager(this, 2)
        binding.shoplistCategory.adapter = categoryAdapter
    }
}