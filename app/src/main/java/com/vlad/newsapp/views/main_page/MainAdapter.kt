package com.vlad.newsapp.views.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.vlad.newsapp.R
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.databinding.ItemPreviewBinding

class MainAdapter : ListAdapter<ItemPreviewNews, PreviewViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = ItemPreviewBinding.inflate(inflater, parent, false)
        return PreviewViewHolder(viewHolder)
    }
    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class PreviewViewHolder(var item: ItemPreviewBinding) : ViewHolder(item.root) {
    fun bind(data: ItemPreviewNews?) {
        item.data = data
        Picasso.get().load(data?.urlImage)
            .placeholder(item.root.context.getDrawable(R.drawable.img)!!)
            .into(item.image)


    }


}


class DiffCallback : DiffUtil.ItemCallback<ItemPreviewNews>() {
    override fun areItemsTheSame(oldItem: ItemPreviewNews, newItem: ItemPreviewNews): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemPreviewNews, newItem: ItemPreviewNews): Boolean {
        return oldItem == newItem
    }
}