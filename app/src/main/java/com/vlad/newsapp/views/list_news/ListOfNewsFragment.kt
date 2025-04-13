package com.vlad.newsapp.views.list_news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.vlad.newsapp.R
import com.vlad.newsapp.data.entity.ItemPreviewNews
import com.vlad.newsapp.databinding.FragmentListOfNewsBinding
import com.vlad.newsapp.views.base.BaseBindingFragment
import com.vlad.newsapp.views.main_page.MainAdapter
import kotlinx.coroutines.launch

class ListOfNewsFragment: BaseBindingFragment<FragmentListOfNewsBinding>(), ListOfArticles {
    override val layoutId: Int
        get() = R.layout.fragment_list_of_news

    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsList.adapter = adapter
    }

    override fun updateListOfArticles(articles: List<ItemPreviewNews>) {
        adapter.submitList(articles)
    }

}