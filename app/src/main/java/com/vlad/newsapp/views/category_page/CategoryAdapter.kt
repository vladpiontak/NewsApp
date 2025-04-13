package com.vlad.newsapp.views.category_page


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.ItemCategoryBinding
import com.vlad.newsapp.databinding.ItemPreviewBinding

class CategoryAdapter(val onSelectedItem: ((value: String) -> Unit), val defaultValue: String): ListAdapter<String, CategoryViewHolder>(CategoryDiffUtil()) {
    private var selectedItemView: RadioButton? = null
    private var selectedItem: String = defaultValue
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.view.item.isChecked = if (selectedItem == getItem(position)){
            selectedItemView = holder.view.item
            true
        }else{
            false
        }

        holder.view.item.setOnClickListener {

           selectedItemView?.isChecked = false

            selectedItemView = holder.view.item.apply {
                isChecked = true
            }

            selectedItem = getItem(position)
            onSelectedItem?.invoke(selectedItem!!)
        }

    }

}

class CategoryViewHolder(var view: ItemCategoryBinding): RecyclerView.ViewHolder(view.root){
    fun bind(value: String) {
       view.item.setText(value)
    }
}

class CategoryDiffUtil: DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}