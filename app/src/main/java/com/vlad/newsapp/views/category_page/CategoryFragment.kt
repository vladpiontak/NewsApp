package com.vlad.newsapp.views.category_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.FragmentCategoryBinding
import com.vlad.newsapp.views.base.BaseBindingFragment
import com.vlad.newsapp.views.category_page.NewsDialogFragment.Companion.DEFAULT_VALUE
import com.vlad.newsapp.views.category_page.NewsDialogFragment.Companion.NEWS_DIALOG_DATA
import com.vlad.newsapp.views.category_page.NewsDialogFragment.Companion.REQUEST_KEY
import com.vlad.newsapp.views.category_page.NewsDialogFragment.Companion.RESULT_KEY
import com.vlad.newsapp.views.list_news.ListOfNewsFragment
import com.vlad.newsapp.views.main_page.MainViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList

class CategoryFragment : BaseBindingFragment<FragmentCategoryBinding>() {
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var listFragment:ListOfNewsFragment
    override val layoutId: Int
        get() = R.layout.fragment_category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFragment = childFragmentManager.findFragmentById(R.id.list_fragment) as ListOfNewsFragment

        lifecycleScope.launch(){
            viewModel.data.collect{

                Log.d("ee", "data" + it)
                listFragment.updateListOfArticles(it)
            }
        }

        binding.btnCategory.setOnClickListener {
            val categories = resources.getStringArray(R.array.array_category).toList()
            showDialogF(categories, REQUEST_KEY_CATEGORY, viewModel.filterByCategory.value)
        }

        binding.btnLanguage.setOnClickListener {
            val language = resources.getStringArray(R.array.array_language).toList()
            showDialogF(language, REQUEST_KEY_LANGUAGE, viewModel.filterByLanguage.value)
        }

        binding.btnCountry.setOnClickListener {
            val country = resources.getStringArray(R.array.array_country).toList()
            showDialogF(country, REQUEST_KEY_COUNTRY, viewModel.filterByCountry.value)
        }
    }

    private fun showDialogF(categories: List<String>, requestKey: String, defaultValue: String){
        val fm = childFragmentManager.apply {
            setFragmentResultListener(requestKey, viewLifecycleOwner) { key, bundle ->
                val result = bundle.getString(RESULT_KEY) ?: ""
                when(key){
                    REQUEST_KEY_CATEGORY -> viewModel.filterByCategory.tryEmit(result)
                    REQUEST_KEY_LANGUAGE -> viewModel.filterByLanguage.tryEmit(result)
                    REQUEST_KEY_COUNTRY -> viewModel.filterByCountry.tryEmit(result)

                }

            }
        }
        NewsDialogFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(NEWS_DIALOG_DATA,  ArrayList(categories.toList()))
                putString(REQUEST_KEY,  requestKey)
                putString(DEFAULT_VALUE,  defaultValue)
            }

        }.show(fm, DIALOG_CATEGORY)
    }
    companion object {
        private const val DIALOG_CATEGORY = "DialogCategory"
        private const val REQUEST_KEY_CATEGORY = "requestKeyCategory"
        private const val REQUEST_KEY_LANGUAGE = "requestKeyLanguage"
        private const val REQUEST_KEY_COUNTRY = "requestKeyCountry"

    }
}