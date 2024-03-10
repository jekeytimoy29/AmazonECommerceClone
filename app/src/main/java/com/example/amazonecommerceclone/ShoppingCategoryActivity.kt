package com.example.amazonecommerceclone

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.amazonecommerceclone.adapter.CategoryAdapter
import com.example.amazonecommerceclone.databinding.ActivityShoppingCategoryBinding
import com.example.amazonecommerceclone.model.CategoryType
import com.example.amazonecommerceclone.model.Product
import com.example.amazonecommerceclone.model.ProductCategory
import com.example.amazonecommerceclone.model.User

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    private lateinit var user: User
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getSerializableExtraProvider<User>("user") != null) {
            user = intent.getSerializableExtraProvider("user")!!
        }

        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeText.text = getString(R.string.welcome_text, user.name)

        val categoryList =
            listOf(
                ProductCategory(
                    "Home and Kitchen",
                    R.drawable.kitchen,
                    CategoryType.HOME_AND_KITCHEN,
                    mutableListOf()
                ),
                ProductCategory(
                    "Beauty and Personal Care",
                    R.drawable.nail,
                    CategoryType.PERSONAL_CARE,
                    mutableListOf()
                ),
                ProductCategory(
                    "Pet supplies", R.drawable.pet_food, CategoryType.PET_SUPPLIES,
                    mutableListOf()
                ),
                ProductCategory(
                    "Toys and Games", R.drawable.lego, CategoryType.TOYS_AND_GAMES,
                    mutableListOf()
                ),
                ProductCategory(
                    "Electronic Gadgets",
                    R.drawable.gadgets,
                    CategoryType.ELECTRONIC_GADGETS,
                    mutableListOf(
                        Product(
                            "iPad",
                            "iPad Pro 11-inch",
                            R.drawable.ipad,
                            R.drawable.apple_icon,
                            700.00
                        ),
                        Product(
                            "iPhone",
                            "iPhone 15s Ultra",
                            R.drawable.iphone,
                            R.drawable.apple_icon,
                            2199.99
                        ),
                        Product(
                            "MacBook M3 Pro",
                            "12-core CPU \n18-core GPU",
                            R.drawable.computer,
                            R.drawable.apple_icon,
                            2500.00
                        ),
                        Product("Dell Inspiron",
                            "13th Gen Intel® Core™ i7",
                        R.drawable.laptop,
                        R.drawable.dell,
                        1499.99),
                        Product(
                            "Dell Keyboard",
                            "Dell Gaming Keyboard Limited Edition",
                            R.drawable.keyboard,
                            R.drawable.dell,
                            14.99
                        ),
                        Product(
                            "Dell Mouse",
                            "Dell Gaming Mouse Limited Edition",
                            R.drawable.mouse,
                            R.drawable.dell,
                            7.00
                        ),
                        Product("JVC Bluetooth Headset",
                            "Bluetooth Headphones with Hybrid Noise Cancelling",
                            R.drawable.headset,
                            R.drawable.jvc,
                            299.99)
                    )
                )
            )

        categoryAdapter = CategoryAdapter(categoryList) { category ->
            Toast.makeText(
                this,
                getString(R.string.category_selected, category.name),
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, ProductsListActivity::class.java).apply {
                val extras = Bundle()
                extras.putSerializable("user", user)
                extras.putSerializable("productCategory", category)
                putExtras(extras)
            }
            startActivity(intent)
        }

        binding.shoplistCategory.layoutManager = GridLayoutManager(this, 2)
        binding.shoplistCategory.adapter = categoryAdapter
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