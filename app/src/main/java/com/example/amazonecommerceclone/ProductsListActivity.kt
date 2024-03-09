package com.example.amazonecommerceclone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazonecommerceclone.adapter.ProductAdapter
import com.example.amazonecommerceclone.databinding.ActivityProductsListBinding
import com.example.amazonecommerceclone.model.ProductCategory
import com.example.amazonecommerceclone.model.User
import com.google.gson.Gson

class ProductsListActivity : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var productCategory: ProductCategory
    private lateinit var binding: ActivityProductsListBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getSerializableExtraProvider<User>("user") != null) {
            user = intent.getSerializableExtraProvider("user")!!
        }

        if (intent.getSerializableExtraProvider<ProductCategory>("productCategory") != null) {
            productCategory = intent.getSerializableExtraProvider("productCategory")!!
        }

        binding = ActivityProductsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (productCategory.productsList.size > 0) {
            binding.noProductsText.visibility = View.GONE
            binding.productsListView.visibility = View.VISIBLE

            productAdapter = ProductAdapter(productCategory.productsList) { product ->
                val intent = Intent(this, ProductDetailsActivity::class.java).apply {
                    val jsonString = Gson().toJson(product)
                    putExtra("product", jsonString)
                }
                startActivity(intent)
            }

            binding.productsListView.layoutManager = LinearLayoutManager(this)
            binding.productsListView.adapter = productAdapter

        } else {
            binding.noProductsText.visibility = View.VISIBLE
            binding.productsListView.visibility = View.GONE
        }
    }

    @Suppress("DEPRECATION")
    private inline fun <reified T : java.io.Serializable> Intent.getSerializableExtraProvider(
        identifierParameter: String
    ): T? {

        return if (Build.VERSION.SDK_INT >= 33) {
            this.getSerializableExtra(identifierParameter, T::class.java)
        } else {
            this.getSerializableExtra(identifierParameter) as T?
        }

    }
}