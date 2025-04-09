package com.vlad.newsapp.views.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vlad.newsapp.data.entity.ItemPreviewNews
import com.vlad.newsapp.databinding.ItemPreviewBinding

class MainAdapter: ListAdapter<ItemPreviewNews, PreviewViewHolder>(DiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = ItemPreviewBinding.inflate(inflater, parent, false)
        return PreviewViewHolder(viewHolder)

    }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        return holder.bind(null)
    }


}

class PreviewViewHolder(itemPreviewBinding: ViewDataBinding): ViewHolder(itemPreviewBinding.root){
    fun bind(data: ItemPreviewNews?) {

    }

    }



class DiffCallback: DiffUtil.ItemCallback<ItemPreviewNews>(){
     override fun areItemsTheSame(oldItem: ItemPreviewNews, newItem: ItemPreviewNews): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemPreviewNews, newItem: ItemPreviewNews): Boolean {
        return oldItem == newItem
    }
}