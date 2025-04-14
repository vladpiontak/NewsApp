package com.vlad.newsapp.views.list_news

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vlad.newsapp.R
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.databinding.FragmentListOfNewsBinding
import com.vlad.newsapp.views.base.BaseBindingFragment

class ListOfNewsFragment: BaseBindingFragment<FragmentListOfNewsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_list_of_news

    var updatePage: (() -> Unit)? = null
    var onClickItem: ((data: ItemPreviewNews) -> Unit)? = null
    private val adapter = MainAdapter()//MainAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsList.adapter = adapter
        settingRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        adapter?.onClickItem = onClickItem
    }
    private fun settingRecyclerView(){
        binding.newsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                    firstVisibleItemPosition >= 0
                ) {
                    updatePage?.invoke()
                }
            }
        })

    }
    fun updateListOfArticles(articles: List<ItemPreviewNews>) {
        adapter.submitList(articles)
    }

}