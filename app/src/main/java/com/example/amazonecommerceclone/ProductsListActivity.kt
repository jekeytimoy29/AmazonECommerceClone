package com.example.amazonecommerceclone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazonecommerceclone.adapter.ProductAdapter
import com.example.amazonecommerceclone.databinding.ActivityProductsListBinding
import com.example.amazonecommerceclone.model.ProductCategory
import com.example.amazonecommerceclone.model.User
import com.google.gson.Gson
import java.text.NumberFormat

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

            productAdapter = ProductAdapter(
                productCategory.productsList,
                { product ->
                    val intent = Intent(this, ProductDetailsActivity::class.java).apply {
                        val jsonString = Gson().toJson(product)
                        putExtra("product", jsonString)
                    }
                    startActivity(intent)
                },
                { product ->
                    user.cartItemsList?.add(product)
                    Toast.makeText(
                        this,
                        getString(R.string.added_item_in_cart, product.name),
                        Toast.LENGTH_LONG
                    ).show()
                }
            )

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

    fun viewCartOnClick(view: View) {
        val total = user.cartItemsList?.sumOf { it.price }
        val cartItems: Array<String> = user.cartItemsList?.map {
            "${it.name}: ${
                NumberFormat.getCurrencyInstance().format(it.price)
            }"
        }!!.toTypedArray()

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle(
                getString(
                    R.string.my_shopping_cart_total,
                    NumberFormat.getCurrencyInstance().format(total).toString()
                )
            )
            .setPositiveButton(getString(R.string.ok_text)) { dialog, which ->
                // Do something.
            }
            .setItems(cartItems) { dialog, which ->
                // Do something on item tapped.
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}