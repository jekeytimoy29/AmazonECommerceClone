package com.example.amazonecommerceclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonecommerceclone.databinding.ItemCategoryBinding
import com.example.amazonecommerceclone.model.ProductCategory

class CategoryAdapter(
    private val categoryList: List<ProductCategory>,
    private val onCategoryClick: (ProductCategory) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = categoryList[position]
                    onCategoryClick(item)
                }
            }
        }

        fun bind(category: ProductCategory) {
            binding.categoryImage.setImageResource(category.imgSrc)
            binding.categoryName.text = category.name
        }

    }
}
