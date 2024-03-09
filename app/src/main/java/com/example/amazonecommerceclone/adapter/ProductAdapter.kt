package com.example.amazonecommerceclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonecommerceclone.databinding.ItemProductBinding
import com.example.amazonecommerceclone.model.Product
import java.text.NumberFormat

class ProductAdapter(
    private val productsList: List<Product>,
    private val onProductClick: (Product) -> Unit,
    private val onProductAddClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = productsList[position]
                    onProductClick(item)
                }
            }
        }

        fun bind(product: Product) {
            binding.productImage.setImageResource(product.imgSrc)
            binding.brandLogo.setImageResource(product.logoSrc)
            binding.productName.text = product.name
            binding.productDescription.text = product.description
            binding.productPrice.text =
                NumberFormat.getCurrencyInstance().format(product.price).toString()
            binding.addButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = productsList[position]
                    onProductAddClick(item)
                }
            }
        }
    }
}